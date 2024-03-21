package org.example.Encoders

import org.example.CaseClass.{MeteoritosCaseClass, SalmonesCaseClass, TortillaCaseClass}
import org.apache.spark.sql.Encoders
import org.apache.spark.sql.Encoder

object CustomEncoders {

  implicit val tortillaEncoder: Encoder[TortillaCaseClass] = Encoders.product[TortillaCaseClass]
  implicit val salmonEncoder: Encoder[SalmonesCaseClass] = Encoders.product[SalmonesCaseClass]
  implicit val meteoritosEncoder: Encoder[MeteoritosCaseClass] = Encoders.product[MeteoritosCaseClass]

}
