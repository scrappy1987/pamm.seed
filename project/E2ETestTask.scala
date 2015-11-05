import java.io.File
import java.util.Date

import sbt._
import scala.sys.process.Process
import java.io._

object E2ETestTask extends Build {
  lazy val endToEndTest = taskKey[Unit]("endToEndTest")

  def endToEndTestTask = {
    if (!checkAsyncProcessesRunning(E2ETestTaskConstants.PORTS, E2ETestTaskConstants.LOGICAL_OR, false)) {
      startAsyncProcesses(E2ETestTaskConstants.ASYNC_PROCESS_SCRIPTS)
      if (readyForTest(E2ETestTaskConstants.PORTS)) {
        startSyncProcessNoArgs(E2ETestTaskConstants.PROTRACTOR_TEST_SCRIPT, E2ETestTaskConstants.PROTRACTOR_TEST_SCRIPT + E2ETestTaskConstants.OUTPUT_LOG_SUFFIX, true)
        stopAsyncProcesses(E2ETestTaskConstants.PORTS)
      }
    }
    else {
      System.out.println(E2ETestTaskConstants.PORTS_IN_USE_ERROR);
    }
  }

  def startAsyncProcesses(asyncProcesses: Array[String]) = {
    asyncProcesses.foreach(f => {
      startAsyncProcess(f, f + E2ETestTaskConstants.OUTPUT_LOG_SUFFIX)
    })
  }

  def startAsyncProcess(commandFile: String, outputFile: String) = {
    (Process(getDerivedFileName(commandFile)) #>> new File(getOutputFile(outputFile))).run()
  }

  def stopAsyncProcesses(ports: Array[String]) = {
    ports.foreach(f => {
      stopAsyncProcess(f, f + E2ETestTaskConstants.OUTPUT_LOG_SUFFIX)
    })
  }

  def stopAsyncProcess(processPort: String, outputFile: String): Unit = {
    startSyncProcessWithArgs(E2ETestTaskConstants.STOP_PROCESS_SCRIPT, processPort, outputFile, false)
  }

  def startSyncProcessNoArgs(commandFile: String, outputFile: String, toStdOut: Boolean) = {
    startAsyncProcess(getDerivedFileName(commandFile), outputFile, toStdOut)
  }

  def startSyncProcessWithArgs(commandFile: String, parms: String, outputFile: String, toStdOut: Boolean) = {
    startAsyncProcess(getDerivedFileName(commandFile) + " " + parms, outputFile, toStdOut)
  }

  def startAsyncProcess(command: String, outputFile: String, toStdOut: Boolean) = {
    val file = new File(getOutputFile(outputFile))
    (Process(command) #>> file).!
    if (toStdOut) streamToStdOut(file)
  }

  def streamToStdOut(file: File) = {
    val fr = new FileReader(file)
    new BufferedReader(fr).lines().toArray.foreach(l => {
      System.out.println(l)
    })
  }

  def readyForTest(ports: Array[String]): Boolean = {
    val startTime = new Date().getTime
    var readyForTest = false
    while ((new Date().getTime - startTime < E2ETestTaskConstants.MAX_WAIT) && !readyForTest) {
      if (checkAsyncProcessesRunning(ports, E2ETestTaskConstants.LOGICAL_AND, true)) readyForTest = true
      Thread.sleep(E2ETestTaskConstants.WAIT_INTERVAL)
    }
    printReadyForTestStatus(readyForTest)
    readyForTest
  }

  def printReadyForTestStatus(readyForTest: Boolean) = {
    if (readyForTest) System.out.println(E2ETestTaskConstants.ALL_PROCESSES_STARTED) else System.out.println(E2ETestTaskConstants.PROCESSES_NOT_STARTED)
  }

  def checkAsyncProcessesRunning(ports: Array[String], f: (Boolean, Boolean) => Boolean, initialFoldValue: Boolean): Boolean = {
    ports.map(p => {
      checkAsyncProcess(p)
    }).foldLeft(initialFoldValue)((a, b) => f(a, b))
  }

  def checkAsyncProcess(port: String): Boolean = {
    val pb = Process(getDerivedFileName(E2ETestTaskConstants.CHECK_PROCESS_SCRIPT))
    val baos = new ByteArrayOutputStream
    val code = (pb #> baos).!
    var processRunning = false
    if (baos.toString.contains(E2ETestTaskConstants.LOCAL_ADDRESS + port)) processRunning = true
    processRunning
  }

  def getOutputFile(outputFileName: String): String = {
    val file = new File(getOutputFolder() + File.separatorChar + outputFileName)
    if (!file.exists()) file.createNewFile()
    file.getAbsolutePath
  }

  def getOutputFolder(): String = {
    val testDir = new File(E2ETestTaskConstants.E2E_RESULTS_FOLDER + File.separatorChar + E2ETestTaskConstants.TEST_RUNTIME)
    if (!testDir.exists()) testDir.mkdirs()
    testDir.getAbsolutePath
  }

  def getDerivedFileName(fileName: String): String = {
    E2ETestTaskConstants.SCRIPT_FOLDER + fileName + E2ETestTaskConstants.SCRIPT_EXTENSION
  }
}
