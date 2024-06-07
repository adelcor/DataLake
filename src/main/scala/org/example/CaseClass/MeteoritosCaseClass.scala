package org.example.CaseClass

/**
 * Case class representing the data structure for meteorite information.
 *
 * @param name       The name of the meteorite.
 * @param id         The unique identifier for the meteorite.
 * @param nametype   The type of name classification.
 * @param recclass   The classification of the meteorite.
 * @param mass       The mass of the meteorite in grams.
 * @param fall       The fall status of the meteorite (e.g., "Fell" or "Found").
 * @param year       The year the meteorite was found or observed.
 * @param reclat     The latitude where the meteorite was found.
 * @param reclong    The longitude where the meteorite was found.
 * @param Geolocation The geolocation in string format.
 */
case class MeteoritosCaseClass(
                                name: String,
                                id: Int,
                                nametype: String,
                                recclass: String,
                                mass: Double,
                                fall: String,
                                year: Int,
                                reclat: Double,
                                reclong: Double,
                                Geolocation: String
                              )
