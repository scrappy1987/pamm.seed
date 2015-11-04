import java.text.SimpleDateFormat
import java.util.Date;

import sbt._
import scala.sys.process
import scala.sys.process.Process
import sys.process._
import java.io.ByteArrayInputStream;
import java.io.File;

object EndToEndTestTask extends Build {
  lazy val endToEndTest = taskKey[Unit]("endToEndTest")

  lazy val outputFolder = getOutputFolder()

  lazy val operatingSystem = System.getProperty("os.name")

  def endToEndTestTask = {
    startAsyncProcess("buildscripts/batch/startSelenium.bat", "seleniumOutput.log")
    startAsyncProcess("buildscripts/batch/startDatabase.bat", "dbOutput.log")
    startAsyncProcess("buildscripts/batch/runTestSetup.bat", "testSetupOutput.log")
    startAsyncProcess("buildscripts/batch/runSvc.bat", "svcOutput.log")
    waitForStartup
    startSyncProcess("buildscripts/batch/runProtractorTests.bat", "protractorResults.log")
    stopAsyncProcesses("4444","9092","9000", "9001")
  }

  def startAsyncProcess(command: String, outputFile: String) = {
    val pb = Process(command);
    val file = new File(getOutputFile(outputFile))
    (pb #>> file).run()
  }

  def stopAsyncProcesses(ports: String*) = {
    ports.foreach(f => {stopAsyncProcess(f, "stopProcesses.log")})
  }

  def stopAsyncProcess(processPort: String, outputFile: String): Unit = {
    startSyncProcess("buildscripts/batch/killProcess.bat " + processPort, outputFile)
  }

  def waitForStartup = {
    println("Waiting for startup");
    Thread.sleep(45000)
    println("Waited for startup");
  }

  def startSyncProcess(command: String, outputFile: String) = {
    val pb = Process(command)
    val file = new File(getOutputFile(outputFile))
    val code = (pb #>> file).!
  }

  def getOutputFile(outputFileName: String): String = {
    val file = new File(outputFolder + File.separatorChar + outputFileName)
    if (!file.exists()) {
      System.out.println("File path of file to create " + file.getAbsolutePath)
      file.createNewFile()
    }
    return file.getAbsolutePath
  }

  def getOutputFolder(): String = {
    val testDir = new File("test-e2e-results" + File.separatorChar + getTestRuntime())
    if (!testDir.exists()) {
      testDir.mkdirs()
    }
    return testDir.getAbsolutePath
  }

  def getTestRuntime(): String = {
    val sdfDate = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSS");
    val now = new Date();
    val strDate = sdfDate.format(now);
    return strDate;
  }
}
