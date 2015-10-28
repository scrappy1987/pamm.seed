import sbt._
import Keys._

object ClientTest extends Build {


  lazy val clientTest = taskKey[Int]("clientTest")

  def clientTestTask = {
    "./svc/test/unit/node_modules/.bin/karma.cmd start ./svc/test/unit/karma.conf.js" !
  }

  def customTestTask = Def.taskDyn {
    val exitCode = (clientTest in Test).value
    if(exitCode == 0)
      Def.task {
        (Keys.test in Test).value
      }
    else Def.task()
  }
}
