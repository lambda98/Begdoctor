name := """begDoctor"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

scalacOptions += "-feature"

routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  jdbc
    exclude("com.h2database", "h2")
    exclude("com.jolbox", "bonecp")
  , cache, ws, filters
  , "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.2"
  , "com.twitter" %% "util-core" % "6.29.0"
  , "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
  , "commons-codec" % "commons-codec" % "1.10"
  , "net.codingwell" %% "scala-guice" % "4.0.0"
  , "org.postgresql" % "postgresql" % "9.4.1208"
  , "com.google.code.gson" % "gson" % "2.3.1"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.5" % Test
  , "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)

resolvers += Resolver.sonatypeRepo("releases")
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"


fork in run := false

parallelExecution in Test := true
