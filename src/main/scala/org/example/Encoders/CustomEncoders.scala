package org.example.Encoders

import org.example.CaseClass.{SalmonesCaseClass, TortillaCaseClass}
import org.apache.spark.sql.Encoders
import org.apache.spark.sql.Encoder

object CustomEncoders {

  implicit val tortillaEncoder: Encoder[TortillaCaseClass] = Encoders.product[TortillaCaseClass]
  implicit val salmonEncoder: Encoder[SalmonesCaseClass] = Encoders.product[SalmonesCaseClass]

}
