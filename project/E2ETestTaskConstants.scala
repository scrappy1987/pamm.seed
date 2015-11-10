import java.text.SimpleDateFormat
import java.util.Date

import sbt._

object E2ETestTaskConstants extends Build {
  val FOLDER_TIMESTAMP_FORMAT = "yyyy-MM-dd-HH.mm.ss.SSS"

  val TEST_RUNTIME = getTestRuntime()

  val WINDOWS_OS = "Windows"
  
  val WINDOWS_SCRIPTS_FOLDER = "buildscripts/batch/"
  
  val WINDOWS_SCRIPT_EXTENSION = ".bat"
  
  val LINUX_SCRIPTS_FOLDER = "buildscripts/shell/"
  
  val LINUX_SCRIPTS_EXTENSION = ".sh"

  val LOCAL_ADDRESS = "0.0.0.0:"

  val OPERATING_SYSTEM = System.getProperty("os.name")

  val SCRIPT_EXTENSION = getScriptExtension()

  val SCRIPT_FOLDER = getScriptFolder()

  val ASYNC_PROCESS_SCRIPTS = Array("startSelenium", "startDatabase", "runTestSetup", "runSvc")

  val CHECK_PROCESS_SCRIPT = "checkProcess"

  val STOP_PROCESS_SCRIPT = "killProcess"

  val PROTRACTOR_TEST_SCRIPT = "runProtractorTests"

  val E2E_RESULTS_FOLDER = "test-e2e-results"

  val OUTPUT_LOG_SUFFIX = "Output.log"

  val PORTS = Array("4444", "9092", "9000", "9001")

  val ALL_PROCESSES_STARTED = "All Processes Started - Ready For Protractor Tests"

  val PROCESSES_NOT_STARTED = "Protractor Tests cannot be run, dependent processes did not start. Check processes running on the following ports " + PORTS

  val MAX_WAIT = 120000

  val WAIT_INTERVAL = 5000

  val requiredPorts = PORTS.map(p => { p }).foldLeft("")(((a, b) => a + " " + b))

  val PORTS_IN_USE_ERROR = "\nERROR - Cannot Run Protractor tests as there are processes already running on one or more of the required ports" + requiredPorts + ". Stop the processes running on these ports and retry\n"

  val LOGICAL_AND = (a: Boolean, b: Boolean) => a && b

  val LOGICAL_OR = (a: Boolean, b: Boolean) => a || b

  def getTestRuntime(): String = {
    val sdfDate = new SimpleDateFormat(FOLDER_TIMESTAMP_FORMAT);
    sdfDate.format(new Date())
  }

  def getScriptExtension(): String = {
    if (OPERATING_SYSTEM.contains(E2ETestTaskConstants.WINDOWS_OS)) WINDOWS_SCRIPT_EXTENSION else LINUX_SCRIPTS_EXTENSION
  }

  def getScriptFolder(): String = {
    if (OPERATING_SYSTEM.contains(WINDOWS_OS)) WINDOWS_SCRIPTS_FOLDER else LINUX_SCRIPTS_FOLDER
  }
}
