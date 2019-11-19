name := "untitled2"

version := "0.1"

scalaVersion := "2.12.6"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

libraryDependencies ++= Seq(

  "com.typesafe.akka" %% "akka-actor" % "2.5.26",

  "com.typesafe.akka" %% "akka-remote" % "2.5.26",

  "com.typesafe.akka" %% "akka-testkit" % "2.5.26",

  "org.scalafx" %% "scalafx" % "8.0.181-R13",

  "org.scalafx" %% "scalafxml-core-sfx8" % "0.4"

)
