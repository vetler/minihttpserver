package net.roeim.minihttpserver

import java.net.InetSocketAddress

import scala.collection.mutable.HashMap

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer

abstract class SimpleHttpServerBase(val socketAddress: String = "127.0.0.1",
  val port: Int = 8080,
  val backlog: Int = 0) extends HttpHandler {
  private val address = new InetSocketAddress(socketAddress, port)
  private val server = HttpServer.create(address, backlog)
  server.createContext("/", this)

  def respond(exchange: HttpExchange, code: Int = 200, body: String = "") {
    val bytes = body.getBytes
    exchange.sendResponseHeaders(code, bytes.size)
    exchange.getResponseBody.write(bytes)
    exchange.getResponseBody.write("\r\n\r\n".getBytes)
    exchange.getResponseBody.close()
    exchange.close()
  }

  def start() = server.start()

  def stop(delay: Int = 1) = server.stop(delay)
}

abstract class MiniHttpServer extends SimpleHttpServerBase {
  private val mappings = new HashMap[String, (HttpExchange) => Any]

  def get(path: String)(action: (HttpExchange) => Any) = mappings += path -> ((exchange: HttpExchange) => action(exchange))

  def handle(exchange: HttpExchange) = mappings.get(exchange.getRequestURI.getPath) match {
    case None => respond(exchange, 404)
    case Some(action) => try {
      respond(exchange, 200, action(exchange).toString)
    } catch {
      case ex: Exception => respond(exchange, 500, ex.toString)
    }
  }
}
