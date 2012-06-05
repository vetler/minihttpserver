name := "minihttpserver"

organization := "net.roeim.minihttpserver"

version := "0.1"

scalaVersion := "2.9.2"

crossScalaVersions := Seq("2.9.1", "2.9.2")

libraryDependencies ++= Seq(
		    "org.apache.httpcomponents" % "httpclient" % "4.2" % "test",
		    "org.scalatest" %% "scalatest" % "1.8" % "test"
)

publishTo := Some(Resolver.sftp("My Maven Repo", "roeim.net", "/var/www/maven/"))