import Dependencies._

lazy val root = (project in file(".")).
 settings(
   inThisBuild(List(
     organization := "com.example",
     scalaVersion := "2.11.8",
     version      := "0.1.0-SNAPSHOT"
   )),
   name := "Hello",
   libraryDependencies ++= Seq(
     "org.scalatest" % "scalatest_2.11" % "3.0.1" % "test",
     "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test,
     "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
   )
 )