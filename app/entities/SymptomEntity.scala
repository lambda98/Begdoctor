package entities

import models.Symptom

/**
  * Created by champillon on 7/13/16.
  */
case class SymptomEntity(id: Long
                         , name: String) {

  def toModel(): Symptom = Symptom(
    id = id
    , name = name
  )
}