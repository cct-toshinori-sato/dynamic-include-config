import java.net.{URLStreamHandler, URLStreamHandlerFactory}

class DynamicIncludeConfigURLStreamHandlerFactory extends URLStreamHandlerFactory {

  def createURLStreamHandler(protocol: String): URLStreamHandler = {
    protocol match {
      case p if p.startsWith("diconfig") => new DynamicIncludeConfigURLStreamHandler
      case _ => null
    }
  }

}