package entities

import models.User
import org.joda.time.DateTime

/**
  * Created by champillon on 9/9/16.
  */
case class UserEntity(id: Long
                      , name: String
                      , surname: String
                      , email: String
                      , avatar: String
                      , mobile: String
                      , created: DateTime) {
  def toModel(): User = User(
    id = id
    , name = name
    , surname = surname
    , email = email
    , avatar = avatar
    , mobile
    , created = created
  )

}
