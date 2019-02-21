import java.io.InputStream
import java.net.{URL, URLConnection}

class DynamicIncludeConfigConnection(url: URL) extends URLConnection(url) {

  def connect(): Unit = {}

  override def getInputStream: InputStream = mkInputStream(url)

  private def mkInputStream(url: URL): InputStream = {
    var configPath = s"${url.getHost}${url.getPath}"
    """\$\{([^\$\{]*)\}""".r.findAllIn(configPath).matchData.foreach { m =>
      configPath = configPath.replace(m.group(0), resolveEnvironment(m.group(1)))
    }
    Option(getClass.getResourceAsStream(configPath)).getOrElse {
      throw new Exception(s"fail to load config file [$configPath]")
    }
  }

  private def resolveEnvironment(envName: String): String = {
    val property = Option(System.getProperty(envName))
    val system = Option(System.getenv(envName))
    (property, system) match {
      case (Some(env), _) => env
      case (_, Some(env)) => env
      case _ => throw new Exception(s"dynamic include env value not found [$envName]")
    }
  }

}