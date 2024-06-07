package org.example.CaseClass

/**
 * Case class representing the data structure for tortilla price information.
 *
 * @param State              The state where the data was recorded.
 * @param City               The city where the data was recorded.
 * @param Year               The year the data was recorded.
 * @param Month              The month the data was recorded (1-12).
 * @param Day                The day of the month the data was recorded (1-31).
 * @param Store_Type         The type of store where the price was recorded.
 * @param Price_per_kilogram The price of tortilla per kilogram.
 */
case class TortillaCaseClass(
                              State: String,
                              City: String,
                              Year: Int,
                              Month: Int,
                              Day: Int,
                              Store_Type: String,
                              Price_per_kilogram: Double
                            )
