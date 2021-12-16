package com.testAutomation.core

import com.opencsv.CSVReader
import com.testAutomation.utils.DataClass
import com.testAutomation.utils.HtmlReporter
import com.testAutomation.utils.ProjectConstants
import java.util.logging.Logger
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.testng.ITestContext
import org.testng.annotations.*
import java.io.FileReader
import java.lang.Exception

/**
 * Description:
 *    Driver class
 *    @author Sonu Gupta
 */

open class BaseTestScript {
    private val logger: Logger = Logger.getLogger("BaseTestScript")
    var webDriver: RemoteWebDriver? =null
    lateinit var testData: DataClass

    @BeforeSuite
    fun beforeSuite(){
        logger.info("In before suite")

    }

    @BeforeClass
    fun beforeClass(iTestContext: ITestContext) {
        logger.info("beforeClass")
        val browserType: String = iTestContext.currentXmlTest.getParameter("device")
        when (browserType.toUpperCase()) {
            "CHROME" -> getChromeDriver()
        }
    }

    private fun getChromeDriver() {
        print("inside chrome driver initialisation")
        try{
            val chromeOptions = ChromeOptions()
            System.setProperty("webdriver.chrome.driver", ProjectConstants.CHROMEDRIVER_PATH)
            webDriver = ChromeDriver(chromeOptions)
        }
        catch (exception: Exception){
            logger.info("unable to start webdriver. Exception: [$exception]")
        }
    }

    @DataProvider(name="DP")
    fun testDataProvider():Array<Array<DataClass>>? {
        logger.info("In testDataProvider")
        val reader = CSVReader(FileReader("./src/test/resources/testData.csv"))
        val arrayList:List<Array<String>> = reader.readAll()
        val testData: Array<Array<DataClass>> = Array(arrayList.size-1) { Array(1) { DataClass("","") } }
        for (i in 1 until arrayList.size){
            println("$i items ${arrayList[i][1]}")
            testData[i-1][0]= DataClass(arrayList[i-0][0],arrayList[i-0][1])
        }
        return testData
    }


    @AfterMethod
    fun afterMethod() {
        logger.info("afterMethod")
        HtmlReporter.extentReports.flush()
    }

    @AfterClass
    fun afterClass() {
        logger.info("afterClass")
        webDriver?.close()
    }

}