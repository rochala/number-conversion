scalaVersion := "2.13.3"

name := "hello-world"
organization := "ch.epfl.scala"
version := "1.0"

val akkaHttpVersion = "10.2.4"
val akkaVersion = "2.6.13"
val circeVersion = "0.12.3"


libraryDependencies ++= Seq(
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    "org.scalatest"          %% "scalatest"                % "3.2.5",
    "com.typesafe.akka"      %% "akka-http"                % akkaHttpVersion,
    "com.typesafe.akka"      %% "akka-stream"              % akkaVersion,
    "com.typesafe.akka"      %% "akka-actor-typed"         % akkaVersion,
    "org.slf4j"               % "slf4j-nop"                % "1.6.4",
    "io.circe"               %% "circe-core"               % circeVersion,
    "io.circe"               %% "circe-parser"             % circeVersion,
    "de.heikoseeberger"      %% "akka-http-circe"          % "1.36.0",
    )
