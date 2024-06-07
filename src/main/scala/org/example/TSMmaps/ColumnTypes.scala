package org.example.TSMmaps

import org.apache.spark.sql.types.{DataType, IntegerType, DoubleType}

/**
 * Object containing column types for various datasets.
 */
object ColumnTypes {

  /**
   * Column types for the salmon dataset.
   * Maps the column names to their corresponding data types.
   */
  val salmonTypesMap: Map[String, DataType] = Map(
    "District ID" -> IntegerType,
    "Report order" -> IntegerType,
    "Year" -> IntegerType,
    "Month number" -> IntegerType,
    "Wild MSW number" -> IntegerType,
    "Wild MSW weight (kg)" -> DoubleType,
    "Wild 1SW number" -> IntegerType,
    "Wild 1SW weight (kg)" -> DoubleType,
    "Sea trout number" -> IntegerType,
    "Sea trout weight (kg)" -> DoubleType,
    "Finnock number" -> IntegerType,
    "Finnock weight (kg)" -> DoubleType,
    "Farmed MSW number" -> IntegerType,
    "Farmed MSW weight (kg)" -> DoubleType,
    "Farmed 1SW number" -> IntegerType,
    "Farmed 1SW weight (kg)" -> DoubleType,
    "Netting effort" -> DoubleType
  )

  /**
   * Column types for the tortilla dataset.
   * Maps the column names to their corresponding data types.
   */
  val tortillaTypesMap: Map[String, DataType] = Map(
    "Year" -> IntegerType,
    "Month" -> IntegerType,
    "Day" -> IntegerType,
    "Price per kilogram" -> DoubleType
  )

  /**
   * Column types for the meteorite dataset.
   * Maps the column names to their corresponding data types.
   */
  val meteoritoTypeMap: Map[String, DataType] = Map(
    "id" -> IntegerType,
    "mass" -> DoubleType,
    "year" -> IntegerType,
    "reclat" -> DoubleType,
    "reclong" -> DoubleType
  )
}
