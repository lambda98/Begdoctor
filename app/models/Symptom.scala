package models

import utilities.Json

/**
  * Created by SasimapornSmt on 7/7/2559.
  */
case class Symptom (id: Long
                   , name: String)
  extends Json

case class SymptomList(symptoms: List[Symptom])
  extends Json
