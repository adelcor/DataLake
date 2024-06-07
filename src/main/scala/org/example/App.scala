package org.example

import org.example.Config.SparkConfig.{connectionProperties, spark}
import org.example.DataHandler.Handler.initLoad

/**
 * Main application object that initializes data loading process and stops the Spark session.
 */
object App {

  /**
   * The main method which serves as the entry point of the application.
   * It initializes the data loading process and stops the Spark session.
   *
   * @param args Command line arguments
   */
  def main(args: Array[String]): Unit = {
    // Initialize the data loading process
    initLoad()

    // Stop the Spark session to release resources
    spark.stop()
  }
}
