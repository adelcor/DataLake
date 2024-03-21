package org.example.Config

import org.apache.spark.sql.SparkSession
import org.example.Connection.ConnectionPropertiesSetter.getConnectionProperties
import org.example.Constants.TSMConst
import org.example.SessionBuilder.SparkSessionBuilder.initSparkSession

import java.util.Properties

object SparkConfig {

  implicit val spark: SparkSession = initSparkSession(TSMConst.AppName)
  implicit val connectionProperties: Properties = getConnectionProperties

}
