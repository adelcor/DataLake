package org.example.Connection

import java.util.Properties
import scala.io.Source
import org.example.Constants.TSMConst

object ConnectionPropertiesSetter {
  // Load properties from the file specified in Exampleconst.Propertiespath.
  private val properties: Properties = loadPropertiesFromFile(TSMConst.PropertiesPath)

  /**
   * Loads properties from a file located at the given filePath.
   *
   * @param filePath The path to the properties file.
   * @return A Properties object containing the loaded properties.
   */
  private def loadPropertiesFromFile(filePath: String): Properties = {
    val props = new Properties()
    // Using scala.io.Source to read from the file.
    val source = Source.fromURL(getClass.getResource(filePath))
    // Loading properties from the file.
    props.load(source.bufferedReader())
    source.close()
    props
  }

  /**
   * Retrieves the database connection properties.
   *
   * Extracts user and password properties and encapsulates them into a new Properties object.
   * Note: This method assumes that 'db.user' and 'db.password' are present in the properties file.
   *
   * @return A Properties object containing user and password for the database connection.
   */
  def getConnectionProperties: Properties = {
    val connectionProperties = new Properties()
    val user = properties.getProperty("db.user")
    val password = properties.getProperty("db.password")

    try {
      if (user != null && password != null) {
        connectionProperties.setProperty("user", user)
        connectionProperties.setProperty("password", password)
      } else {
        // Throw an exception if user or password is not defined in properties.
        throw new IllegalStateException("Database user or password not defined in properties.")
      }
    } catch {
      case ex: Exception =>
        // Handle any exceptions that may occur during property extraction.
        println(s"Error setting connection properties: ${ex.getMessage}")
    }

    connectionProperties
  }
}