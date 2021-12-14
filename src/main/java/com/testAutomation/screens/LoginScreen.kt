package com.testAutomation.screens

import com.testAutomation.core.BaseTestPage
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver

/**
 * Description:
 */

class LoginScreen(webDriver: RemoteWebDriver) : BaseTestPage(webDriver) {
    private val defaultTimeOutInSeconds = 10
    private val usernameTextbox = By.name("username")
    private val passwordTextbox = By.name("password")
    private val loginButton = By.cssSelector("input.button")

    fun hasScreenLoaded(): Boolean {
        return isElementDisplayedAfterTimeout(usernameTextbox, defaultTimeOutInSeconds)
    }

    fun enterUsername(username: String): Boolean {
        if (isElementDisplayedAfterTimeout(usernameTextbox, defaultTimeOutInSeconds)) {
            sendKeys(usernameTextbox,username)
            return true
        } else {
            return false
        }
    }

    fun enterPassword(password: String): Boolean {
        if (isElementDisplayedAfterTimeout(passwordTextbox, defaultTimeOutInSeconds)) {
            sendKeys(passwordTextbox,password)
            return true
        } else {
            return false
        }
    }

    fun clickLoginButton(): Boolean {
        if (isElementDisplayedAfterTimeout(loginButton, defaultTimeOutInSeconds)) {
            click(loginButton)
            return true
        } else {
            return false
        }
    }

}