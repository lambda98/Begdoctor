package controllers.api

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._

/**
  * Created by champillon on 9/8/16.
  */
case class CreateBookingForm(name: String
                             , surname: String
                             , email: String
                             , mobile: String
                             , hospitalTimeId: Long)

object CreateBookingForm {
  val form = Form(
    mapping(
      "name" -> of[String],
      "surname" -> of[String],
      "email" -> of[String],
      "mobile" -> of[String],
      "hospitalTimeId" -> of[Long]
    )(CreateBookingForm.apply)(CreateBookingForm.unapply)
  )
}

case class CreateHospitalForm(latitude: Float
                              , longitude: Float
                              , name: String)

object CreateHospitalForm {
  val form = Form(
    mapping(
      "latitude" -> of[Float],
      "longitude" -> of[Float],
      "name" -> of[String]
    )(CreateHospitalForm.apply)(CreateHospitalForm.unapply)
  )
}

case class UpdateHospitalForm(latitude: Float
                              , longitude: Float
                              , name: String)

object UpdateHospitalForm {
  val form = Form(
    mapping(
      "latitude" -> of[Float],
      "longitude" -> of[Float],
      "name" -> of[String]
    )(UpdateHospitalForm.apply)(UpdateHospitalForm.unapply)
  )
}