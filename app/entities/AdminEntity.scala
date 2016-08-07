package entities

import models.Admin
import org.joda.time.DateTime
/**
  * Created by anawin on 8/7/2016 AD.
  */
case class AdminEntity (id: Long
                        , name: String
                        , surname: String
                        , email: String
                        , username: String
                        , password: String
                        , hospitalId: Long
                        , created: DateTime){
  def toModel(): Admin = Admin(
    id = id
    , name = name
    , surname = surname
    , email = email
    , username = username
    , password = password
    , hospitalId = hospitalId
    , created = created
  )

}
