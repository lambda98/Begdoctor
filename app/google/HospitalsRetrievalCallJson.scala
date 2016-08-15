package google

import java.io._
import java.util.ArrayList

import com.google.gson.Gson
import org.apache.http._
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.message.BasicNameValuePair

case class Person(firstName: String, lastName: String, age: Int)

class HttpJsonPostTestClass{
  def jsonData = HttpJsonPostTest
}

object HttpJsonPostTest extends App {

  // create our object as a json string
  val spock = new Person("Leonard", "Nimoy", 82)
  val spockAsJson = new Gson().toJson(spock)

  // add name value pairs to a post object
  val post = new HttpPost("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=13.7519144%2C100.5312028&radius=50000&type=hospital&name=hospital&key=AIzaSyAoTD7NaYHQ8vPjlqjdvlr0B_qaJUat9-Q")
  val nameValuePairs = new ArrayList[NameValuePair]()
  nameValuePairs.add(new BasicNameValuePair("JSON", spockAsJson))
  post.setEntity(new UrlEncodedFormEntity(nameValuePairs))

  // send the post request
  val client = new DefaultHttpClient
  val response = client.execute(post)
  println("--- BODY ---")

  val inputStreamReader = new InputStreamReader(response.getEntity.getContent)
  val bufferedReader = new BufferedReader(inputStreamReader)

  val str = Stream.continually(bufferedReader.readLine()).takeWhile(_ != null).mkString("\n")
  println("result: "+str)
  str
}
