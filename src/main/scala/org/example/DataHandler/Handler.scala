package org.example.DataHandler

import org.apache.spark.sql.SparkSession
import org.example.CaseClass.{SalmonesCaseClass, TortillaCaseClass, MeteoritosCaseClass}
import org.example.Encoders.CustomEncoders.{tortillaEncoder, salmonEncoder, meteoritosEncoder}
import org.example.ProcessData.Process.processDatasetAzurite
import org.example.Config.readConfig.{salmonConfig, tortillasConfig, meteoritosConfig}

import java.util.Properties

/**
 * Object responsible for handling the data loading process.
 */
object Handler {

  /**
   * Initializes the data loading process.
   *
   * @param spark               Implicit Spark session.
   * @param connectionProperties Implicit connection properties.
   */
  def initLoad()(implicit spark: SparkSession, connectionProperties: Properties): Unit = {

    // Process the salmon dataset using the configuration
    processDatasetAzurite[SalmonesCaseClass](salmonConfig)

    // Process the tortilla dataset using the configuration
    processDatasetAzurite[TortillaCaseClass](tortillasConfig)

    // Process the meteorite dataset using the configuration
    processDatasetAzurite[MeteoritosCaseClass](meteoritosConfig)

    //    processDataset[TortillaCaseClass](TSMConst.tortillasPath, TSMConst.tableNameTortillas, columnMapTortilla)
    //    processDataset[SalmonesCaseClass](TSMConst.salmonesPath, TSMConst.tableNameSalmones, columnMapSalmones)
    //    processDataset[MeteoritosCaseClass](TSMConst.meteoritosPath, TSMConst.tableNameMeteoritos)
  }

}


