package org.example.Config

import org.apache.spark.sql.types.DataType

/**
 * Case class representing the configuration for a dataset.
 *
 * @param filePath      Optional file path where the dataset is located.
 * @param fileName      Optional file name of the dataset.
 * @param containerName Optional container name where the dataset is stored.
 * @param tableName     Name of the table in the dataset.
 * @param columnMap     A map of column names in the dataset to their corresponding new names.
 * @param columnType    A map of column names in the dataset to their corresponding data types.
 */
case class DatasetConfig(
                          filePath: Option[String] = None,
                          fileName: Option[String] = None,
                          containerName: Option[String] = None,
                          tableName: String,
                          columnMap: Map[String, String] = Map.empty,
                          columnType: Map[String, DataType] = Map.empty
                        ) {
  /**
   * Sets the file path for the dataset.
   *
   * @param fp The file path to be set.
   * @return A copy of the current DatasetConfig with the new file path.
   */
  def withFilePath(fp: String): DatasetConfig = this.copy(filePath = Some(fp))

  /**
   * Sets the file name for the dataset.
   *
   * @param fn The file name to be set.
   * @return A copy of the current DatasetConfig with the new file name.
   */
  def withFileName(fn: String): DatasetConfig = this.copy(fileName = Some(fn))

  /**
   * Sets the container name for the dataset.
   *
   * @param cn The container name to be set.
   * @return A copy of the current DatasetConfig with the new container name.
   */
  def withContainerName(cn: String): DatasetConfig = this.copy(containerName = Some(cn))

  /**
   * Sets the column map for the dataset.
   *
   * @param cm The column map to be set.
   * @return A copy of the current DatasetConfig with the new column map.
   */
  def withColumnMap(cm: Map[String, String]): DatasetConfig = this.copy(columnMap = cm)

  /**
   * Sets the column types for the dataset.
   *
   * @param ct The column types to be set.
   * @return A copy of the current DatasetConfig with the new column types.
   */
  def withColumnType(ct: Map[String, DataType]): DatasetConfig = this.copy(columnType = ct)
}
