package org.example.CaseClass

/**
 * Case class representing the data structure for salmon information.
 *
 * @param District             The district where the data was recorded.
 * @param District_ID          The unique identifier for the district.
 * @param Report_order         The order of the report.
 * @param Region               The region where the district is located.
 * @param Method               The method used for data collection.
 * @param Year                 The year the data was collected.
 * @param Month                The month the data was collected.
 * @param Month_number         The numeric representation of the month.
 * @param Wild_MSW_number      The number of wild multi-sea-winter salmon.
 * @param Wild_MSW_weight      The weight of wild multi-sea-winter salmon in kilograms.
 * @param Wild_1SW_number      The number of wild one-sea-winter salmon.
 * @param Wild_1SW_weight      The weight of wild one-sea-winter salmon in kilograms.
 * @param Sea_trout_number     The number of sea trout.
 * @param Sea_trout_weight     The weight of sea trout in kilograms.
 * @param Finnock_number       The number of finnock.
 * @param Finnock_weight       The weight of finnock in kilograms.
 * @param Farmed_MSW_number    The number of farmed multi-sea-winter salmon.
 * @param Farmed_MSW_weight    The weight of farmed multi-sea-winter salmon in kilograms.
 * @param Farmed_1SW_number    The number of farmed one-sea-winter salmon.
 * @param Farmed_1SW_weight    The weight of farmed one-sea-winter salmon in kilograms.
 * @param Netting_effort       The effort spent on netting, typically measured in hours or days.
 */
case class SalmonesCaseClass(
                              District: String,
                              District_ID: Int,
                              Report_order: Int,
                              Region: String,
                              Method: String,
                              Year: Int,
                              Month: String,
                              Month_number: Int,
                              Wild_MSW_number: Int,
                              Wild_MSW_weight: Double,
                              Wild_1SW_number: Int,
                              Wild_1SW_weight: Double,
                              Sea_trout_number: Int,
                              Sea_trout_weight: Double,
                              Finnock_number: Int,
                              Finnock_weight: Double,
                              Farmed_MSW_number: Int,
                              Farmed_MSW_weight: Double,
                              Farmed_1SW_number: Int,
                              Farmed_1SW_weight: Double,
                              Netting_effort: Double
                            )
