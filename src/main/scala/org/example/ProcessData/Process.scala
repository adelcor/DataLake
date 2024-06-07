package org.example.ProcessData

import org.apache.spark.sql.{DataFrame, Dataset, Encoder, SaveMode, SparkSession}
import org.example.Constants.TSMConst
import java.util.Properties
import org.asynchttpclient.Dsl._
import org.apache.spark.sql.types.DataType
import org.apache.spark.sql.functions.col
import org.example.Config.DatasetConfig

/**
 * Object responsible for processing datasets, including reading from CSV files or Azurite and writing to a JDBC database.
 */
object Process {

  /**
   * Processes a dataset from a CSV file.
   *
   * @param filePath       Path to the CSV file.
   * @param tableName      Name of the table to write the dataset to.
   * @param columnMappings Optional column mappings for renaming columns.
   * @param spark          Implicit Spark session.
   * @param enc            Implicit encoder for the dataset type.
   * @param connectionProperties Implicit connection properties for the JDBC database.
   * @tparam T Type of the dataset.
   */
  def processDataset[T](filePath: String, tableName: String, columnMappings: Map[String, String] = Map.empty)
                       (implicit spark: SparkSession, enc: Encoder[T], connectionProperties: Properties): Unit = {
    val dataset: Dataset[T] = readCsvAsDataset(filePath, columnMappings)
    writeToJdbc(dataset, TSMConst.jdbcURL, tableName)
  }

  /**
   * Processes a dataset from Azurite.
   *
   * @param config Configuration for the dataset.
   * @param spark  Implicit Spark session.
   * @param enc    Implicit encoder for the dataset type.
   * @param connectionProperties Implicit connection properties for the JDBC database.
   * @tparam T Type of the dataset.
   */
  def processDatasetAzurite[T](config: DatasetConfig)
                              (implicit spark: SparkSession, enc: Encoder[T], connectionProperties: Properties): Unit = {
    val dataset: Dataset[T] = readFromAzuriteAsDataset(config)
    writeToJdbc(dataset, TSMConst.jdbcURL, config.tableName)
  }

  /**
   * Reads a CSV file as a dataset.
   *
   * @param filePath       Path to the CSV file.
   * @param columnMappings Optional column mappings for renaming columns.
   * @param spark          Implicit Spark session.
   * @param enc            Implicit encoder for the dataset type.
   * @tparam T Type of the dataset.
   * @return The dataset read from the CSV file.
   */
  private def readCsvAsDataset[T](filePath: String, columnMappings: Map[String, String])
                                 (implicit spark: SparkSession, enc: Encoder[T]): Dataset[T] = {
    val df = spark.read.option("header", "true").option("inferSchema", "true").csv(filePath)
    val renamedDF = renameColumns(df, columnMappings)
    renamedDF.as[T]
  }

  /**
   * Renames columns in a DataFrame.
   *
   * @param data        DataFrame to rename columns in.
   * @param columnNames Map of old column names to new column names.
   * @return DataFrame with renamed columns.
   */
  def renameColumns(data: DataFrame, columnNames: Map[String, String]): DataFrame = {
    columnNames.foldLeft(data) { case (df, (oldName, newName)) =>
      df.withColumnRenamed(oldName, newName)
    }
  }

  /**
   * Writes a dataset to a JDBC database.
   *
   * @param dataset         Dataset to write.
   * @param jdbcUrl         JDBC URL of the database.
   * @param tableName       Name of the table to write to.
   * @param connectionProperties Connection properties for the JDBC database.
   * @tparam T Type of the dataset.
   */
  private def writeToJdbc[T](dataset: Dataset[T], jdbcUrl: String, tableName: String)
                            (implicit connectionProperties: Properties): Unit = {
    dataset.write.mode(SaveMode.Append).jdbc(jdbcUrl, tableName, connectionProperties)
  }

  /**
   * Reads a dataset from Azurite.
   *
   * @param config Configuration for the dataset.
   * @param spark  Implicit Spark session.
   * @param enc    Implicit encoder for the dataset type.
   * @tparam T Type of the dataset.
   * @return The dataset read from Azurite.
   */
  def readFromAzuriteAsDataset[T](config: DatasetConfig)
                                 (implicit spark: SparkSession, enc: Encoder[T]): Dataset[T] = {
    import spark.implicits._

    val client = asyncHttpClient()

    try {
      (config.containerName, config.fileName) match {
        case (Some(cn), Some(fn)) =>
          val response = client.prepareGet(s"http://localhost:7071/api/MyHttpTrigger?filename=$fn&container=$cn")
            .execute().toCompletableFuture().join()
          if (response.getStatusCode == 200) {
            val jsonData = response.getResponseBody
            val jsonDataset = spark.createDataset(Seq(jsonData))
            val data = spark.read.json(jsonDataset)
            val convertedData = convertColumnTypes(data, config.columnType)
            val renamedData = renameColumns(convertedData, config.columnMap)
            renamedData.as[T]
          } else {
            throw new Exception(s"Error in request: ${response.getStatusText}")
          }
        case (None, _) => throw new IllegalArgumentException("containerName cannot be None for readFromAzuriteAsDataset")
        case (_, None) => throw new IllegalArgumentException("fileName cannot be None for readFromAzuriteAsDataset")
      }
    } finally {
      client.close()
    }
  }

  /**
   * Converts column types in a DataFrame.
   *
   * @param data        DataFrame to convert column types in.
   * @param columnTypes Map of column names to their new data types.
   * @return DataFrame with converted column types.
   */
  def convertColumnTypes(data: DataFrame, columnTypes: Map[String, DataType]): DataFrame = {
    columnTypes.foldLeft(data) { case (df, (columnName, dataType)) =>
      df.withColumn(columnName, col(columnName).cast(dataType))
    }
  }
}
