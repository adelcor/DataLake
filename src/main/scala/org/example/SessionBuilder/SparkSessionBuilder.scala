package org.example.SessionBuilder

import org.apache.spark.sql.SparkSession

/**
 * Object responsible for building and initializing the Spark session.
 */
object SparkSessionBuilder {

  /**
   * Initializes and returns a Spark session.
   *
   * @param appName The name of the application.
   * @return The initialized Spark session.
   */
  def initSparkSession(appName: String): SparkSession = {
    SparkSession.builder()
      .appName(appName)
      .master("local[*]")  // Use all available cores
      .getOrCreate()
  }

}
