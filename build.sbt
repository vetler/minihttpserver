name := "minihttpserver"

organization := "net.roeim.minihttpserver"

version := "0.2"

scalaVersion := "2.10.3"

crossScalaVersions := Seq("2.9.1", "2.9.2", "2.9.3", "2.10.1", "2.10.2")

libraryDependencies ++= Seq(
		    "org.apache.httpcomponents" % "httpclient" % "4.3.1" % "test",
		    "org.scalatest" %% "scalatest" % "1.9.2" % "test"
)

publishTo := Some(Resolver.sftp("My Maven Repo", "roeim.net", 30000, "/var/www/maven/"))