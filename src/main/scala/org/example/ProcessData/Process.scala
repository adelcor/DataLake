package org.example.ProcessData

import org.apache.spark.sql.{DataFrame, Dataset, Encoder, SaveMode, SparkSession}
import org.example.Constants.TSMConst

import java.util.Properties
import org.asynchttpclient.Dsl._

import org.apache.spark.sql.types.{DataType}
import org.apache.spark.sql.functions.col
import org.example.TSMmaps._
import org.example.Encoders.CustomEncoders.meteoritosEncoder
import org.example.Encoders.CustomEncoders.salmonEncoder
import org.example.Encoders.CustomEncoders.tortillaEncoder
import org.example.Config.DatasetConfig


object Process {

  def processDataset[T](filePath: String, tableName: String, columnMappings: Map[String, String] = Map.empty)(implicit spark: SparkSession, enc: Encoder[T], connectionProperties: Properties): Unit = {
    val dataset:Dataset[T] = readCsvAsDataset(filePath, columnMappings)
    writeToJdbc(dataset, TSMConst.jdbcURL, tableName)
  }

  def processDatasetAzurite[T](config: DatasetConfig)(implicit spark: SparkSession, enc: Encoder[T], connectionProperties: Properties): Unit = {
    val dataset:Dataset[T] = readFromAzuriteAsDataset(config)
    writeToJdbc(dataset, TSMConst.jdbcURL, config.tableName)
  }

  private def readCsvAsDataset[T](filePath: String, columnMappings: Map[String, String])(implicit spark: SparkSession, enc: Encoder[T]): Dataset[T] = {
    val df = spark.read.option("header", "true").option("inferSchema", "true").csv(filePath)
    val renamedDF = renameColumns(df, columnMappings)
    renamedDF.as[T]
  }

  def renameColumns(data: DataFrame, columnNames: Map[String, String]): DataFrame = {
    columnNames.foldLeft(data) { case (df, (oldName, newName)) =>
      df.withColumnRenamed(oldName, newName)
    }
  }

  private def writeToJdbc[T](dataset: Dataset[T], jdbcUrl: String, tableName: String)(implicit connectionProperties: Properties): Unit = {
    dataset.write.mode(SaveMode.Append).jdbc(jdbcUrl, tableName, connectionProperties)
  }


  //puedo pasar tambien config a readfromazurite y meter los parametros en plan config.loquesea
  def readFromAzuriteAsDataset[T](config: DatasetConfig)(implicit spark: SparkSession, enc: Encoder[T]): Dataset[T] = {
    import spark.implicits._

    val client = asyncHttpClient()

    try {
      (config.containerName, config.fileName) match {
        case (Some(cn), Some(fn)) =>
          val response = client.prepareGet(s"http://localhost:7071/api/MyHttpTrigger?filename=$fn&container=$cn").execute().toCompletableFuture().join()
          if (response.getStatusCode == 200) {
            val jsonData = response.getResponseBody
            val jsonDataset = spark.createDataset(Seq(jsonData))
            val data = spark.read.json(jsonDataset)
            val convertedData = convertColumnTypes(data, config.columnType)
            val renamedData = renameColumns(convertedData, config.columnMap)
            renamedData.as[T]
          } else {
            throw new Exception(s"Error al hacer la solicitud: ${response.getStatusText}")
          }
        case (None, _) => throw new IllegalArgumentException("containerName no puede ser None para readFromAzuriteAsDataset")
        case (_, None) => throw new IllegalArgumentException("fileName no puede ser None para readFromAzuriteAsDataset")
      }
    } finally {
      client.close()
    }
  }


  def convertColumnTypes(data: DataFrame, columnTypes: Map[String, DataType]): DataFrame = {
    columnTypes.foldLeft(data) { case (df, (columnName, dataType)) =>
      df.withColumn(columnName, col(columnName).cast(dataType))
    }
  }
}
