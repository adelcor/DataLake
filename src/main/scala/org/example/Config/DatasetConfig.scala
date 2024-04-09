package org.example.Config
import org.apache.spark.sql.types.{DataType}

case class DatasetConfig(
                             filePath: Option[String] = None,
                             fileName: Option[String] = None,
                             containerName: Option[String] = None,
                             tableName: String,
                             columnMap: Map[String, String] = Map.empty,
                             columnType: Map[String, DataType] = Map.empty
                           )
{
  def withFilePath(fp: String): DatasetConfig = this.copy(filePath = Some(fp))
  def withFileName(fn: String): DatasetConfig = this.copy(fileName = Some(fn))
  def withContainerName(cn: String): DatasetConfig = this.copy(containerName = Some(cn))
  def withColumnMap(cm: Map[String, String]): DatasetConfig = this.copy(columnMap = cm)
  def withColumnType(ct: Map[String, DataType]): DatasetConfig = this.copy(columnType = ct)
}
