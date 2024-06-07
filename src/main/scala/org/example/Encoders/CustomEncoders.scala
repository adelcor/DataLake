package org.example.Encoders

import org.example.CaseClass.{MeteoritosCaseClass, SalmonesCaseClass, TortillaCaseClass}
import org.apache.spark.sql.{Encoders, Encoder}

/**
 * Object containing custom encoders for case classes used in the application.
 */
object CustomEncoders {

  /**
   * Implicit encoder for the TortillaCaseClass.
   */
  implicit val tortillaEncoder: Encoder[TortillaCaseClass] = Encoders.product[TortillaCaseClass]

  /**
   * Implicit encoder for the SalmonesCaseClass.
   */
  implicit val salmonEncoder: Encoder[SalmonesCaseClass] = Encoders.product[SalmonesCaseClass]

  /**
   * Implicit encoder for the MeteoritosCaseClass.
   */
  implicit val meteoritosEncoder: Encoder[MeteoritosCaseClass] = Encoders.product[MeteoritosCaseClass]

}
