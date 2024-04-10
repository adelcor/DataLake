package org.example.DataHandler

import org.apache.spark.sql.SparkSession
import org.example.CaseClass.SalmonesCaseClass
import org.example.CaseClass.TortillaCaseClass
import org.example.CaseClass.MeteoritosCaseClass
import org.example.Constants.TSMConst
import org.example.Encoders.CustomEncoders.tortillaEncoder
import org.example.Encoders.CustomEncoders.salmonEncoder
import org.example.Encoders.CustomEncoders.meteoritosEncoder
import org.example.ProcessData.Process.{processDataset, processDatasetAzurite}
import org.example.TSMmaps.ColumnMaps.{columnMapSalmones, columnMapTortilla}
import org.example.TSMmaps.ColumnTypes.salmonTypesMap
import org.example.TSMmaps.ColumnTypes.meteoritoTypeMap
import org.example.TSMmaps.ColumnTypes.tortillaTypesMap
import org.example.Config.DatasetConfig
import org.example.Config.readConfig.{salmonConfig, tortillasConfig, meteoritosConfig}

import java.util.Properties

object Handler {

  def initLoad()(implicit spark: SparkSession, connectionProperties: Properties): Unit = {


    processDataset[TortillaCaseClass](TSMConst.tortillasPath, TSMConst.tableNameTortillas, columnMapTortilla)
    processDataset[SalmonesCaseClass](TSMConst.salmonesPath, TSMConst.tableNameSalmones, columnMapSalmones)
    processDataset[MeteoritosCaseClass](TSMConst.meteoritosPath, TSMConst.tableNameMeteoritos)
    processDatasetAzurite[SalmonesCaseClass](salmonConfig)
    processDatasetAzurite[TortillaCaseClass](tortillasConfig)
    processDatasetAzurite[MeteoritosCaseClass](meteoritosConfig)
  }

}
