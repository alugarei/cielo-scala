name := """cielo-scala"""

version := "0.1"

scalaVersion := "2.11.7"

javacOptions ++= Seq("-encoding", "UTF-8")

organization := "com.alugarei"


// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.5"
libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.2.1"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.4.6"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"
