# MiniHttpServer

A minimal library for embedding an HTTP server, written in Scala. Mostly copied from
[this answer](http://stackoverflow.com/a/6432180/2935) by the user [Tommy](http://stackoverflow.com/users/52898/tommy)
at [StackOverflow](http://stackoverflow). Note that this isn't an implementation of an HTTP server, it just uses the one available in the JDK.

This project is [cc-by-sa](http://creativecommons.org/licenses/by-sa/2.5/) licensed.

## How to use

Assuming you use SBT, add the following resolver and library dependencies:

    :::scala
    resolvers += "Roeim.net repository" at "http://roeim.net/maven"

    libraryDependencies += "net.roeim.minihttpserver" %% "minihttpserver" % "1.0"


Next, extend *MiniHttpServer* and define routes, as shown here in an example taken from the test code:

    :::scala
    class TestServer extends MiniHttpServer {
      get("/") { exchange => 
        exchange.getResponseHeaders().add("Content-type", "text/html")
        "It works!"
      }

      get("/foo") { exchange =>
        exchange.getResponseHeaders().add("Content-type", "text/plain")
        "And here's foo."
      }
    }

After this, it needs to be started, as follows:

    :::scala
    val server = new TestServer
    server.start()
    // ...
    // and when you're done:
    server.stop()

GL&HF!
