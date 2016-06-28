package models

import utilities.Json

/**
  * Created by anawin on 6/23/2016 AD.
  */
case class User(id: Long, name: String, surname: String, email: String)
  extends Json
