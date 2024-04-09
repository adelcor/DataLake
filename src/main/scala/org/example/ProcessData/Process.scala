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

  def processDatasetAzurite[T](fileName: String, containerName: String, tableName: String, columnMap: Map[String, String] = Map.empty, columnType: Map[String, DataType] = Map.empty)(implicit spark: SparkSession, enc: Encoder[T], connectionProperties: Properties): Unit = {
    val dataset:Dataset[T] = readFromAzuriteAsDataset(containerName, fileName, columnMap, columnType)
    writeToJdbc(dataset, TSMConst.jdbcURL, tableName)
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

  def readFromAzuriteAsDataset[T](containerName: String, fileName: String, columnMap: Map[String, String], columnType: Map[String, DataType] )(implicit spark: SparkSession, enc: Encoder[T]): Dataset[T] = {
    import spark.implicits._

    val client = asyncHttpClient()

    try {
      val response = client.prepareGet(s"http://localhost:7071/api/MyHttpTrigger?filename=$fileName&container=$containerName").execute().toCompletableFuture().join()
      if (response.getStatusCode == 200) {
        val jsonData = response.getResponseBody
        val jsonDataset = spark.createDataset(Seq(jsonData))
        val data = spark.read.json(jsonDataset)
        val convertedData = convertColumnTypes(data, columnType)
        val renamedData = renameColumns(convertedData, columnMap)
        renamedData.as[T]
      } else {
        throw new Exception(s"Error al hacer la solicitud: ${response.getStatusText}")
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
