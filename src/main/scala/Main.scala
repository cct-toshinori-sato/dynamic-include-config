import java.net.URL

import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

case class MyConfig(name: String)

object Main extends App {
  println("aaaaaaaaaaaa")
  URL.setURLStreamHandlerFactory(new DynamicIncludeConfigURLStreamHandlerFactory)
  println("aaaaaaaaaaaa")
  val config = ConfigFactory.load()
  val myConfig = config.as[MyConfig]("myConfig")
  println(s"hello, ${myConfig.name}")
}
