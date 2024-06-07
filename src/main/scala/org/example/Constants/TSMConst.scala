package org.example.Constants

/**
 * Object containing constants used throughout the application.
 */
object TSMConst {

  /**
   * Path to the properties file.
   */
  val PropertiesPath: String = "/application.properties"

  /**
   * Name of the application.
   */
  val AppName: String = "TSM"

  /**
   * JDBC URL for connecting to the PostgreSQL database.
   */
  val jdbcURL: String = "jdbc:postgresql://localhost:5433/"

  /**
   * Table name for tortilla data.
   */
  val tableNameTortillas: String = "tortillas"

  /**
   * Table name for salmon data.
   */
  val tableNameSalmones: String = "salmones"

  /**
   * Table name for meteorite data.
   */
  val tableNameMeteoritos: String = "meteoritos"

  /**
   * File path to the tortilla prices CSV file.
   */
  val tortillasPath: String = "C:/Users/antonio.delcorral_bl/CSV_S/archive (1)/tortilla_prices.csv/"

  /**
   * File path to the salmon and sea trout nets CSV file.
   */
  val salmonesPath: String = "C:/Users/antonio.delcorral_bl/CSV_S/archive (1)/SalmonandSeaTroutNets1952-2022.csv/"

  /**
   * File path to the meteorite landings CSV file.
   */
  val meteoritosPath: String = "C:/Users/antonio.delcorral_bl/CSV_S/archive (1)/meteorite-landings.csv/"

  /**
   * File name for the salmon CSV file.
   */
  val fileSalmones: String = "SalmonandSeaTroutNets1952-2022.csv"

  /**
   * File name for the tortilla CSV file.
   */
  val fileTortillas: String = "tortilla_prices.csv"

  /**
   * File name for the meteorite CSV file.
   */
  val fileMeteoritos: String = "meteorite-landings.csv"

  /**
   * Name of the container.
   */
  val containerMicont: String = "micontenedor"

  /**
   * Test table name for salmon data.
   */
  val tabletestSalmones: String = "SalmonesAZ"

  /**
   * Test table name for tortilla data.
   */
  val tabletestTortillas: String = "TortillasAZ"

  /**
   * Test table name for meteorite data.
   */
  val tabletestMeteoritos: String = "MeteoritosAZ"
}
