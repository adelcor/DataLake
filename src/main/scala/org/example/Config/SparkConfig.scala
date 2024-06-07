package org.example.Config

import org.apache.spark.sql.SparkSession
import org.example.Connection.ConnectionPropertiesSetter.getConnectionProperties
import org.example.Constants.TSMConst
import org.example.SessionBuilder.SparkSessionBuilder.initSparkSession

import java.util.Properties

/**
 * Object containing the configuration for Spark and connection properties.
 */
object SparkConfig {

  /**
   * Implicit Spark session initialized with the application name from constants.
   */
  implicit val spark: SparkSession = initSparkSession(TSMConst.AppName)

  /**
   * Implicit connection properties for the database connection.
   */
  implicit val connectionProperties: Properties = getConnectionProperties
}
