# MiniHttpServer

A minimal library for embedding an HTTP server, written in Scala.

## How to use

Assuming you use SBT, add the following resolver and library dependencies:

    :::scala
    resolvers += "Roeim.net repository" at "http://roeim.net/maven"

    libraryDependencies ++= Seq(
      "net.roeim.minihttpserver" %% "minihttpserver" % "0.1"
    )


Next, extend *MiniHttpServer* and define routes, as shown here in an example taken from the test code:

    :::scala
    class TestServer extends MiniHttpServer {
      get("/")((exchange: HttpExchange) => {
        exchange.getResponseHeaders().add("Content-type", "text/html")
        "It works!"
      })

      get("/foo")((exchange: HttpExchange) => {
        exchange.getResponseHeaders().add("Content-type", "text/plain")
        "And here's foo."
      })
    }

After this, it needs to be started, as follows:

    :::scala
    val server = new TestServer
    server.start()
    // ...
    // and when you're done:
    server.stop()

GL&HF!
