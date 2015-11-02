import sbt._
import scala.sys.process.Process

object EndToEndTestTask extends Build {
    lazy val endToEndTest = taskKey[Unit]("endToEndTest")

    lazy val OS = System.getProperty("os.name");

    def endToEndTestTask =  {
        startWebDriverManager

        startH2Database

        runTestSetup

        runSvc

        waitForStartup

        runProtractorTests
    }


    def startH2Database {
        val pb = Process("buildscripts/batch/startDatabase.bat")

        val p = pb.run
    }


    def runTestSetup = {
        val pb = Process("buildscripts/batch/runTestSetup.bat")

        val p = pb.run
    }


    def runSvc = {
       val pb = Process("buildscripts/batch/runSvc.bat")

        val p = pb.run
    }


    def startWebDriverManager = {
        val pb = Process("buildscripts/batch/startSelenium.bat")

        val p = pb.run
    }


    def waitForStartup = {
        println("Waiting for startup");

        Thread.sleep(120000)

        println("Waited for startup");
    }


    def runProtractorTests = {
        val pb = Process("buildscripts/batch/runProtractorTests.bat")

        val p = pb.!
    }

    def loadDatabase = {
//        val conn = DB
//        try {
//            val stmt = conn.createStatement
//            val rs = stmt.executeQuery("SELECT 9 as testkey ")
//            while (rs.next()) {
//                outString += rs.getString("testkey")
//            }
//        } finally {
//            conn.close()
//        }
    }
}
