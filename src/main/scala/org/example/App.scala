package org.example

import org.apache.spark.sql.SparkSession
import org.example.Connection.ConnectionPropertiesSetter.getConnectionProperties
import org.example.SessionBuilder.SparkSessionBuilder._
import org.example.Constants._
import org.example.DataHandler.Handler.initLoad
import java.util.Properties

object App {

  def main(args: Array[String]): Unit = {

    implicit val spark: SparkSession = initSparkSession(TSMConst.AppName)
    implicit val connectionProperties: Properties = getConnectionProperties

    initLoad()

    spark.stop()
  }
}
