package org.example.Connection

import java.util.Properties
import scala.io.Source
import org.example.Constants.TSMConst

/**
 * Object responsible for setting the connection properties for the database.
 */
object ConnectionPropertiesSetter {

  // Load properties from the specified file path
  private val properties: Properties = loadPropertiesFromFile(TSMConst.PropertiesPath)

  /**
   * Loads properties from a file.
   *
   * @param filePath The path to the properties file.
   * @return A Properties object containing the loaded properties.
   */
  private def loadPropertiesFromFile(filePath: String): Properties = {
    val props = new Properties()
    val source = Source.fromURL(getClass.getResource(filePath))
    props.load(source.bufferedReader())
    source.close()
    props
  }

  /**
   * Retrieves the connection properties for the database.
   *
   * @return A Properties object containing the connection properties.
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
        throw new IllegalStateException("Database user or password not defined in properties.")
      }
    } catch {
      case ex: Exception =>
        println(s"Error setting connection properties: ${ex.getMessage}")
    }

    connectionProperties
  }
}
