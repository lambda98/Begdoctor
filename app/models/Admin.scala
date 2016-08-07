package models

import org.joda.time.DateTime
import utilities.Json

/**
  * Created by anawin on 8/6/2016 AD.
  */
case class Admin (id: Long
                  , name: String
                  , surname: String
                  , email: String
                  , username: String
                  , password: String
                  , hospitalId: Long
                  , created: DateTime)
  extends Json

case class AdminList(admins: List[Admin])
  extends Json
