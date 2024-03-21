package org.example.ProcessData

import org.apache.spark.sql.{DataFrame, Dataset, Encoder, SaveMode, SparkSession}
import org.example.Constants.TSMConst
import java.util.Properties

object Process {

  def processDataset[T](filePath: String, tableName: String, columnMappings: Map[String, String] = Map.empty)(implicit spark: SparkSession, enc: Encoder[T], connectionProperties: Properties): Unit = {
    val dataset = readCsvFileAsDS(filePath, columnMappings)
    writeToJdbc(dataset, TSMConst.jdbcURL, tableName)
  }

  private def readCsvFileAsDS[T](filePath: String, columnMappings: Map[String, String])(implicit spark: SparkSession, enc: Encoder[T]): Dataset[T] = {
    val df = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(filePath)
      .transform(applyColumnMappings(columnMappings))

    df.as[T]
  }

  private def applyColumnMappings(columnMappings: Map[String, String])(df: DataFrame): DataFrame = {
    columnMappings.foldLeft(df) { case (currentDF, (originalName, newName)) =>
      currentDF.withColumnRenamed(originalName, newName)
    }
  }

  private def writeToJdbc[T](ds: Dataset[T], jdbcUrl: String, tableName: String)(implicit connectionProperties: Properties): Unit = {
    ds.write
      .mode(SaveMode.Append)
      .jdbc(jdbcUrl, tableName, connectionProperties)
  }

}
