name := """cielo-scala"""

version := "0.3"

scalaVersion := "2.11.7"

javacOptions ++= Seq("-encoding", "UTF-8")

organization := "com.alugarei"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.5"
libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.2.1"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.4.6"

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>https://github.com/alugarei/cielo-scala</url>
    <licenses>
      <license>
        <name>The MIT License (MIT)</name>
        <url>https://raw.githubusercontent.com/alugarei/cielo-scala/master/LICENSE</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:alugarei/cielo-scala.git</url>
      <connection>scm:git:git@github.com:alugarei/cielo-scala.git</connection>
    </scm>
    <developers>
      <developer>
        <id>gustavoramos00</id>
        <name>Gustavo</name>
        <url>https://github.com/gustavoramos00</url>
      </developer>
    </developers>)