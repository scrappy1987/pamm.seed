import sbt._
import Keys._

object CustomTest extends Build {


  lazy val jsTest = taskKey[Int]("jsTest")

  def jsTestTask = {
    "./svc/test/unit/node_modules/.bin/karma.cmd start ./svc/test/unit/karma.conf.js" !
  }

  def customTestTask = Def.taskDyn {
    val exitCode = (jsTest in Test).value
    if(exitCode == 0)
      Def.task {
        (Keys.test in Test).value
      }
    else Def.task()
  }
}
