package models

import org.joda.time.DateTime
import utilities.Json

/**
  * Created by anawin on 6/23/2016 AD.
  */
case class User(id: Long
                , name: String
                , surname: String
                , email: String
                , avatar: String
                , mobile: String
                , created: DateTime)
  extends Json
