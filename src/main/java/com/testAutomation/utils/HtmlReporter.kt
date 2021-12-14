package com.testAutomation.utils

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.reporter.ExtentHtmlReporter
import com.aventstack.extentreports.reporter.configuration.ChartLocation
import com.aventstack.extentreports.reporter.configuration.Theme
import java.sql.Timestamp

/**
 * Description:
 *    Reporter class
 */

object HtmlReporter {
    private val extentHtmlReporter: ExtentHtmlReporter = ExtentHtmlReporter("./TestReport${Timestamp(System.currentTimeMillis())}.html")
    val extentReports: ExtentReports = ExtentReports()

    init {
        extentHtmlReporter.config().reportName = "Test Report"
        extentHtmlReporter.config().documentTitle = "Test Automation Report"
        extentHtmlReporter.config().theme = Theme.STANDARD
        extentHtmlReporter.config().testViewChartLocation = ChartLocation.BOTTOM
        extentReports.attachReporter(extentHtmlReporter)
    }

    fun createTest(testName: String): ExtentTest {
        return extentReports.createTest(testName)
    }
}