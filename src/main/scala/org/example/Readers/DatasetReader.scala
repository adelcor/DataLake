package org.example.Readers

import org.apache.spark.sql.{Dataset, Encoder, SparkSession}
import org.example.Config.DatasetConfig

/**
 * Trait defining a reader for datasets.
 *
 * @tparam T The type of the dataset.
 */
trait DatasetReader[T] {

  /**
   * Reads a dataset based on the provided configuration.
   *
   * @param config The configuration for the dataset.
   * @param spark  Implicit Spark session.
   * @param enc    Implicit encoder for the dataset type.
   * @return The dataset read based on the provided configuration.
   */
  def readDataset(config: DatasetConfig)(implicit spark: SparkSession, enc: Encoder[T]): Dataset[T]
}
