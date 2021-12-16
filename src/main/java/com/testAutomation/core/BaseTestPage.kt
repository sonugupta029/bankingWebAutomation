package com.testAutomation.core

import java.util.logging.Logger
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait
import java.lang.Exception

/**
 * Description:
 *    Reusable methods for POM
 *    @author Sonu Gupta
 */

open class BaseTestPage(private val webDriver: RemoteWebDriver) {

    val logger: Logger = Logger.getLogger("BaseTestPage")

    fun isElementDisplayedAfterTimeout(objectLocator: By, timeoutInSeconds: Int): Boolean {
        val wait = WebDriverWait(webDriver, timeoutInSeconds.toLong())
        return try {
            wait.until {
                ExpectedCondition { isElementDisplayed(objectLocator) }
            }
            true
        } catch (e: TimeoutException) {
            logger.warning("isMobileElementDisplayedAfterTimeout - time out waiting for element to be displayed: [$objectLocator]")
            false
        }

    }

     fun isElementDisplayed(objectLocator: By): Boolean {
        val webElement: WebElement? = getWebElement(objectLocator)
        return webElement != null && webElement.isDisplayed

    }


    fun click(objectLocator: By) {
        val elementToClick = getWebElement(objectLocator)
        elementToClick?.let {
            it.click()
            logger.info("clicked on element $objectLocator")
        }
    }

    fun getWebElement(objectLocator: By): WebElement? {
        var webElement: WebElement? = null
        try {
            webElement = webDriver.findElement(objectLocator)
        } catch (e: Exception) {
            logger.info("Exception occured while finding element: [${e.message}]")
        }
        return webElement
    }

    fun getLabelText(objectToFind: By): String? {
        val labelElement: WebElement? = getWebElement(objectToFind)
        var labelText: String? = null
        if (labelElement != null) {
            labelText = labelElement.text
        }
        return labelText
    }

     fun swipeTo(x: Int, y: Int): Boolean {
        return try {
            val je = webDriver as JavascriptExecutor
            je.executeScript("window.scrollBy($x,$y)", "");
            true
        } catch (e: Exception) {
            logger.info("Exception occurred while swiping: [${e.message}]")
            false
        }
    }

     fun getElementAttribute(objectToFind: By, attributeName: String): String? {
        val element: WebElement? = getWebElement(objectToFind)
        var attribute: String? = null
        if (element != null) {
            logger.info("getElementAttribute - Found requested element: $objectToFind")
            try {
                attribute = element.getAttribute(attributeName)
            }catch (e: Exception){
                logger.info("getElementAttribute - Exception occurred while getting attribute: [${e.message}]")
            }
        }
        logger.info("getElementAttribute - Retrieved attribute for name $attributeName as $attribute")
        return attribute
    }

    fun sendKeys(objectLocator: By, keysToSent: String){
        getWebElement(objectLocator)?.let {
            it.sendKeys(keysToSent)
        }
    }
}