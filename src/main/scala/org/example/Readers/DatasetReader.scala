package org.example.Readers

import org.apache.spark.sql.{Dataset, Encoder, Encoders, SparkSession}
import org.example.Config.DatasetConfig
import org.example.Encoders._
trait DatasetReader[T] {
  def readDataset(config: DatasetConfig)(implicit spark: SparkSession, enc: Encoder[T]): Dataset[T]
}