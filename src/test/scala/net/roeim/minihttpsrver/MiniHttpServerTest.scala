package net.roeim.minihttpserver

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.scalatest.BeforeAndAfter
import org.scalatest.FunSuite
import com.sun.net.httpserver.HttpExchange
import org.scalatest.BeforeAndAfterAll

class MiniHttpServerTestSuite extends FunSuite with BeforeAndAfterAll {

  // Define a test server that has a few pages we can test against
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

  var server: TestServer = new TestServer

  // Start the server before we start testing
  override def beforeAll(configMap: Map[String, Any]) {
    server.start()
  }

  // Cleanup
  override def afterAll() {
    server.stop()
  }

  def getPage(url: String) = {
    val httpclient = new DefaultHttpClient
    val httpget = new HttpGet(url)
    val response = httpclient.execute(httpget)
    val entity = response.getEntity()
    val instream = entity.getContent()
    (response, scala.io.Source.fromInputStream(instream).getLines().mkString("\n"))
  }

  test("Server can serve plain HTML at root") {
    // Get the page served at localhost:8080/ and make sure it contains the correct text
    val (response, result) = getPage("http://localhost:8080/")
    assert(response.getHeaders("Content-type")(0).getValue() == "text/html")
    assert(result == "It works!")
  }

  test("Server can serve plain plain text at /foo") {
    // Get the page served at localhost:8080/foo and make sure it contains the correct text
    val (response, result) = getPage("http://localhost:8080/foo")
    assert(response.getHeaders("Content-type")(0).getValue() == "text/plain")
    assert(result == "And here's foo.")
  }

}