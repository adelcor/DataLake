package org.example

import org.apache.spark.sql.{DataFrame, Dataset, SaveMode, SparkSession}
import org.example.Connection.ConnectionPropertiesSetter.getConnectionProperties
import org.example.SessionBuilder.SparkSessionBuilder._
import org.example.Constants._
import org.example.CaseClass._
import org.example.Encoders._
import org.apache.spark.sql.Encoder
import org.example.Encoders.CustomEncoders.{salmonEncoder, tortillaEncoder}
import org.example.TSMmaps.ColumnMaps.{columnMapSalmones, columnMapTortilla}

import java.util.Properties

object App {

  def main(args: Array[String]): Unit = {

    implicit val spark: SparkSession = initSparkSession(TSMConst.AppName)
    implicit val connectionProperties: Properties = getConnectionProperties

    val dsTortillas: Dataset[TortillaCaseClass] = readCsvFileAsDS[TortillaCaseClass](TSMConst.tortillasPath, columnMapTortilla)
    val dsSalmones: Dataset[SalmonesCaseClass] = readCsvFileAsDS[SalmonesCaseClass](TSMConst.salmonesPath,columnMapSalmones)

  //  val dfSalmones = readCsvFile(TSMConst.salmonesPath)
  //  val dfMeteoritos = readCsvFile(TSMConst.meteoritosPath)

    spark.stop()
  }

  private def readCsvFileAsDS[T](filePath: String, columnMappings: Map[String, String])(implicit spark: SparkSession, enc: Encoder[T]): Dataset[T] = {
    var df = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(filePath)

    columnMappings.foreach { case (originalName, newName) =>
    df = df.withColumnRenamed(originalName, newName)
    }
    df.as[T]
  }

}
