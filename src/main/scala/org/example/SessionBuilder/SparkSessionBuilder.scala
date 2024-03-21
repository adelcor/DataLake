package org.example.SessionBuilder

import org.apache.spark.sql.SparkSession

object SparkSessionBuilder {

  def initSparkSession(appName: String): SparkSession = {
    SparkSession.builder()
      .appName(appName)
      .master("local[*]")
      .getOrCreate()
  }

}
