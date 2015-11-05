import java.text.SimpleDateFormat
import java.util.Date

import sbt._
import scala.sys.process
import scala.sys.process.Process
import sys.process._
import java.io.ByteArrayOutputStream
import java.io.File

object EndToEndTestTask extends Build {
  lazy val endToEndTest = taskKey[Unit]("endToEndTest")

  val TEST_RUNTIME = getTestRuntime()

  val OPERATING_SYSTEM = System.getProperty("os.name")

  val PORTS = Array("4444", "9092", "9000", "9001")

  val ALL_PROCESSES_STARTED = "All Processes Started - Ready For Protractor Tests"

  val PROCESSES_NOT_STARTED = "Protractor Tests cannot be run, dependent processes did not start. Check processes running on the following ports " + PORTS

  val MAX_WAIT = 45000

  val WAIT_INTERVAL = 5000

  def endToEndTestTask = {
    startAsyncProcess("buildscripts/batch/startSelenium.bat", "seleniumOutput.log")
    startAsyncProcess("buildscripts/batch/startDatabase.bat", "dbOutput.log")
    startAsyncProcess("buildscripts/batch/runTestSetup.bat", "testSetupOutput.log")
    startAsyncProcess("buildscripts/batch/runSvc.bat", "svcOutput.log")
    if (readyForTest(PORTS)) {
      startSyncProcess("buildscripts/batch/runProtractorTests.bat", "protractorResults.log")
      stopAsyncProcesses(PORTS)
    }
  }

  def startAsyncProcess(command: String, outputFile: String) = {
    val pb = Process(command);
    val file = new File(getOutputFile(outputFile))
    (pb #>> file).run()
  }

  def stopAsyncProcesses(ports: Array[String]) = {
    ports.foreach(f => {
      stopAsyncProcess(f, "stopProcesses.log")
    })
  }

  def stopAsyncProcess(processPort: String, outputFile: String): Unit = {
    startSyncProcess("buildscripts/batch/killProcess.bat " + processPort, outputFile)
  }

  def startSyncProcess(command: String, outputFile: String) = {
    val pb = Process(command)
    val file = new File(getOutputFile(outputFile))
    val code = (pb #>> file).!
  }

  def readyForTest(ports: Array[String]): Boolean = {
    val startTime = new Date().getTime
    var readyForTest = false
    while ((new Date().getTime - startTime < MAX_WAIT) && !readyForTest) {
      if (checkAsyncProcesses(ports)) {
        readyForTest = true
      }
      Thread.sleep(WAIT_INTERVAL)
    }
    printReadyForTestStatus(readyForTest)
    readyForTest
  }

  def checkAsyncProcesses(ports: Array[String]): Boolean = {
    ports.map(p => {
      checkAsyncProcess(p)
    })
      .foldLeft(true)(_ && _)
  }

  def checkAsyncProcess(port: String): Boolean = {
    val pb = Process("buildscripts/batch/checkProcess.bat")
    val baos = new ByteArrayOutputStream
    val code = (pb #> baos).!
    val opt = baos.toString
    var processRunning = false
    if (opt.contains("0.0.0.0:" + port)) {
      processRunning = true
    }
    processRunning
  }
  
  def printReadyForTestStatus(readyForTest: Boolean) = {
    if (readyForTest) {
      System.out.println(ALL_PROCESSES_STARTED)
    }
    else {
      System.out.println(PROCESSES_NOT_STARTED)
    }
  }

  def getOutputFile(outputFileName: String): String = {
    val file = new File(getOutputFolder() + File.separatorChar + outputFileName)
    if (!file.exists()) {
      file.createNewFile()
    }
    file.getAbsolutePath
  }

  def getOutputFolder(): String = {
    val testDir = new File("test-e2e-results" + File.separatorChar + TEST_RUNTIME)
    if (!testDir.exists()) {
      testDir.mkdirs()
    }
    testDir.getAbsolutePath
  }

  def getTestRuntime(): String = {
    val sdfDate = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSS");
    val now = new Date();
    val strDate = sdfDate.format(now)
    strDate;
  }
}
