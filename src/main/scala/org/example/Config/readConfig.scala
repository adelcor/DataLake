package org.example.Config

import org.example.Constants.TSMConst
import org.example.TSMmaps.ColumnMaps.{columnMapSalmones, columnMapTortilla}
import org.example.TSMmaps.ColumnTypes.{meteoritoTypeMap, salmonTypesMap, tortillaTypesMap}

/**
 * Object containing configurations for different datasets.
 */
object readConfig {

  /**
   * Configuration for the salmon dataset.
   */
  val salmonConfig: DatasetConfig = DatasetConfig(tableName = TSMConst.tabletestSalmones)
    .withFileName(TSMConst.fileSalmones)
    .withContainerName(TSMConst.containerMicont)
    .withColumnMap(columnMapSalmones)
    .withColumnType(salmonTypesMap)

  /**
   * Configuration for the tortilla dataset.
   */
  val tortillasConfig: DatasetConfig = DatasetConfig(tableName = TSMConst.tabletestTortillas)
    .withFileName(TSMConst.fileTortillas)
    .withContainerName(TSMConst.containerMicont)
    .withColumnMap(columnMapTortilla)
    .withColumnType(tortillaTypesMap)

  /**
   * Configuration for the meteorite dataset.
   */
  val meteoritosConfig: DatasetConfig = DatasetConfig(tableName = TSMConst.tabletestMeteoritos)
    .withFileName(TSMConst.fileMeteoritos)
    .withContainerName(TSMConst.containerMicont)
    .withColumnType(meteoritoTypeMap)
}
