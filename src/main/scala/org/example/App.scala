package org.example

import org.example.Config.SparkConfig.{connectionProperties, spark}
import org.example.DataHandler.Handler.initLoad


object App {

  def main(args: Array[String]): Unit = {

    initLoad()
    spark.stop()
  }
}
