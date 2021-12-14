package com.testAutomation.screens

import com.testAutomation.core.BaseTestPage
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver

/**
 * Description:
 */

class AccountServicesScreen(val webDriver: RemoteWebDriver) : BaseTestPage(webDriver) {
    private val defaultTimeOutInSeconds = 10
    private val openNewAccountLink = By.linkText("Open New Account")
    private val selectAccountType = By.xpath("//*[@id='type']/option[1]")
    private val fromAccount = By.xpath("//*[@id='fromAccountId']/option[1]")
    private val openNewAccountButton = By.xpath("//input[@value='Open New Account']")
    private val newAccountNumber = By.id("newAccountId")
    private val accountDetailsAccountID = By.id("accountId")
    private val accountDetailsBalance = By.id("balance")
    private val accountDetailsAvailableBalance = By.id("availableBalance")



    fun hasScreenLoaded(): Boolean {
        return isElementDisplayedAfterTimeout(openNewAccountLink, defaultTimeOutInSeconds)
    }

    fun clickOpenNewAccountLink(): Boolean {
        if (isElementDisplayedAfterTimeout(openNewAccountLink, defaultTimeOutInSeconds)) {
            click(openNewAccountLink)
            return true
        } else {
            return false
        }
    }

    fun getSelectedAccountTypeText(): String? {
        var actualText: String? = null
        if (isElementDisplayedAfterTimeout(selectAccountType, defaultTimeOutInSeconds)) {
            actualText = (getLabelText(selectAccountType))
        }
        logger.info("label for actual text  is $actualText")
        return actualText
    }

    fun getSelectedAccountAttribute(): String? {
        var actualText: String? = null
        if (isElementDisplayedAfterTimeout(selectAccountType, defaultTimeOutInSeconds)) {
            actualText = (getElementAttribute(selectAccountType,"selected"))
        }
        logger.info("label for actual text  is $actualText")
        return actualText
    }

    fun getFromAccountNumber(): String? {
        var actualText: String? = null
        if (isElementDisplayedAfterTimeout(fromAccount, defaultTimeOutInSeconds)) {
            actualText = getLabelText(fromAccount)
        }
        logger.info("label for actual text  is $actualText")
        return actualText
    }

    fun getFromAccountNumberSelectedAttribute(): String? {
        var actualText: String? = null
        if (isElementDisplayedAfterTimeout(fromAccount, defaultTimeOutInSeconds)) {
            actualText = getElementAttribute(fromAccount,"selected")
        }
        logger.info("label for actual text  is $actualText")
        return actualText
    }

    fun clickOpenNewAccountButton(): Boolean {
        if (isElementDisplayedAfterTimeout(openNewAccountButton, defaultTimeOutInSeconds)) {
            click(openNewAccountButton)
            return true
        } else {
            return false
        }
    }

    fun getNewAccountNumber(): String? {
        var actualText: String? = null
        if (isElementDisplayedAfterTimeout(newAccountNumber, defaultTimeOutInSeconds)) {
            actualText = getLabelText(newAccountNumber)
        }
        logger.info("label for actual text  is $actualText")
        return actualText
    }

    fun clickNewAccountNumberLink(): Boolean {
        if (isElementDisplayedAfterTimeout(newAccountNumber, defaultTimeOutInSeconds)) {
            click(newAccountNumber)
            return true
        } else {
            return false
        }
    }

    fun getAccountDetailsAccountNumber(): String? {
        var actualText: String? = null
        if (isElementDisplayedAfterTimeout(accountDetailsAccountID, defaultTimeOutInSeconds)) {
            actualText = getLabelText(accountDetailsAccountID)
        }
        logger.info("label for actual text  is $actualText")
        return actualText
    }

    fun getAccountDetailsAccountBalance(): String? {
        var actualText: String? = null
        if (isElementDisplayedAfterTimeout(accountDetailsBalance, defaultTimeOutInSeconds)) {
            actualText = getLabelText(accountDetailsBalance)
        }
        logger.info("label for actual text  is $actualText")
        return actualText
    }

    fun getAccountDetailsAccountAvailableBalance(): String? {
        var actualText: String? = null
        if (isElementDisplayedAfterTimeout(accountDetailsAvailableBalance, defaultTimeOutInSeconds)) {
            actualText = getLabelText(accountDetailsAvailableBalance)
        }
        logger.info("label for actual text  is $actualText")
        return actualText
    }

}