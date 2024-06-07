package org.example.TSMmaps

/**
 * Object containing column mappings for various datasets.
 */
object ColumnMaps {

  /**
   * Column mappings for the tortilla dataset.
   * Maps the original column names to new column names.
   */
  val columnMapTortilla: Map[String, String] = Map(
    "Store Type" -> "Store_Type",
    "Price per kilogram" -> "Price_per_kilogram"
  )

  /**
   * Column mappings for the salmon dataset.
   * Maps the original column names to new column names.
   */
  val columnMapSalmones: Map[String, String] = Map(
    "District ID" -> "District_ID",
    "Report order" -> "Report_order",
    "Month number" -> "Month_number",
    "Wild MSW number" -> "Wild_MSW_number",
    "Wild MSW weight (kg)" -> "Wild_MSW_weight",
    "Wild 1SW number" -> "Wild_1SW_number",
    "Wild 1SW weight (kg)" -> "Wild_1SW_weight",
    "Sea trout number" -> "Sea_trout_number",
    "Sea trout weight (kg)" -> "Sea_trout_weight",
    "Finnock number" -> "Finnock_number",
    "Finnock weight (kg)" -> "Finnock_weight",
    "Farmed MSW number" -> "Farmed_MSW_number",
    "Farmed MSW weight (kg)" -> "Farmed_MSW_weight",
    "Farmed 1SW number" -> "Farmed_1SW_number",
    "Farmed 1SW weight (kg)" -> "Farmed_1SW_weight",
    "Netting effort" -> "Netting_effort"
  )

}
