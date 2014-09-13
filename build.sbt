name := "minihttpserver"

organization := "net.roeim.minihttpserver"

version := "1.0"

scalaVersion := "2.11.2"

crossScalaVersions := Seq("2.10.4", "2.11.2")

libraryDependencies ++= Seq(
		    "org.apache.httpcomponents" % "httpclient" % "4.3.1" % "test",
		    "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)

publishTo := Some(Resolver.sftp("My Maven Repo", "roeim.net", 30000, "/var/www/maven/"))