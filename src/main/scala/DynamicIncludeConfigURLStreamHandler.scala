import java.net.{URL, URLConnection, URLStreamHandler}

class DynamicIncludeConfigURLStreamHandler extends URLStreamHandler {

  def openConnection(url: URL): URLConnection = {
    new DynamicIncludeConfigConnection(url)
  }

}