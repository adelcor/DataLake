package org.example.TSMmaps
import org.apache.spark.sql.types.{DataType, IntegerType, DoubleType}

object ColumnTypes {

  val salmonTypesMap = Map(
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

  val tortillaTypesMap = Map(
    "Year" -> IntegerType,
    "Month" -> IntegerType,
    "Day" -> IntegerType,
    "Price per kilogram" -> DoubleType
  )

  val meteoritoTypeMap = Map(
    "id" -> IntegerType,
    "mass" -> DoubleType,
    "year" -> IntegerType,
    "reclat" -> DoubleType,
    "reclong" -> DoubleType
  )
}
