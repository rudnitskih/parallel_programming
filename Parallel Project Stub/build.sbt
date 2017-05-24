val deps = Seq("com.storm-enroute" % "scalameter_2.11" % "0.8.2")

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Parallel Tasks",
    libraryDependencies ++= deps
  )
