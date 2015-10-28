import Lib._

lazy val root = (project in file("."))
  .aggregate(svc, int)

lazy val svc = (project in file("svc"))
  .enablePlugins(PlayJava)
  .settings(clientTest:= clientTestTask)
  .settings(Keys.test:= customTestTask.value)
  .settings(Settings.basicSettings: _*)
  .settings(Settings.serviceSettings: _*)
  .settings(libraryDependencies ++= Seq(
    javaJpa, hibernate, cache, javaWs, evolutions
  ) ++ Lib.test(
    junit
  ))

lazy val int = (project in file("int"))
  .settings(Settings.basicSettings: _*)
  .settings(libraryDependencies ++= Lib.compile(
    jaxrs, jaxrsClient, guice
  ) ++ Lib.test(
    cucumberGuice, cucumberJUnit, dbunit, mysqlconn, junit, logback
  ))

ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}

fork in run := true
