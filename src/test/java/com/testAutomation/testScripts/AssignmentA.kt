package com.testAutomation.testScripts

import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.Status
import com.testAutomation.core.BaseTestScript
import com.testAutomation.screens.AccountServicesScreen
import com.testAutomation.screens.LoginScreen
import com.testAutomation.utils.DataClass
import com.testAutomation.utils.HtmlReporter
import org.testng.Assert
import org.testng.annotations.Factory
import org.testng.annotations.Test

/**
 * Description:
 *    Scenario:
 *    a) Verification of opening two new accounts for both account types - CHECKING and SAVINGS.
Help - Click the “Open New Account” link in the “Account Services” section
 *    b) Verification of the account details page (click on the account number after creating it) for both the new accounts created as part of the above step.
Sample account details page - https://parabank.parasoft.com/parabank/activity.htm?id=12345
 *     c) Bill Pay (Link is placed in the Account Services section):
- Automate the verification of the form.
- Fill in the Payee Information and transfer the whole amount of $200.00 from any one of the newly created
accounts to the other new one. Verify that the Balance and transaction table details are correct in each of
the accounts (both sender and receiver account) activity page (ex: https://parabank.parasoft.com/parabank/activity.htm?id=12345) *
 */

class AssignmentA : BaseTestScript {
    lateinit var loginScreen: LoginScreen
    lateinit var extentTest: ExtentTest
    lateinit var accountServicesScreen: AccountServicesScreen

    @Factory(dataProvider = "DP")
    constructor (testData: DataClass) : this() {
        this.testData = testData
    }

    constructor()

    @Test(priority = 0)
    fun verifyAccountOpening() {

        extentTest = HtmlReporter.createTest("Login with $testData")


        if (webDriver == null) {
            extentTest.log(Status.FAIL, "initialisation Failed ")
            Assert.fail()

        } else {
            extentTest.log(Status.PASS, "initialisation pass ")

        }

        webDriver?.get("https://parabank.parasoft.com/parabank/index.htm")
        webDriver?.manage()?.window()?.maximize()
        loginScreen = LoginScreen(webDriver!!)

        val actualDashboardScreenTitle = webDriver?.title
        val expectedDashboardScreenTitle = "ParaBank | Welcome | Online Banking"

        if (loginScreen.hasScreenLoaded() && (actualDashboardScreenTitle == expectedDashboardScreenTitle)) {
            extentTest.log(
                Status.PASS,
                "Dashboard page loaded successfully. Actual: [$actualDashboardScreenTitle], Expected: [$expectedDashboardScreenTitle]"
            )
        } else {
            extentTest.log(
                Status.FAIL,
                "Page loaded failed. Actual: [$actualDashboardScreenTitle], Expected: [$expectedDashboardScreenTitle]"
            )
            Assert.fail()

        }

        if (loginScreen.enterUsername(testData.USER)) {
            extentTest.log(Status.PASS, "Entered username: [${testData.USER}]")

        } else {
            extentTest.log(Status.FAIL, "Entered username: [${testData.USER}] failed")
            Assert.fail()

        }

        if (loginScreen.enterPassword(testData.Pass)) {
            extentTest.log(Status.PASS, "Entered password: [${testData.Pass}]")

        } else {
            extentTest.log(Status.FAIL, "Entered password: [${testData.Pass}] failed")
            Assert.fail()


        }



        if (loginScreen.clickLoginButton()) {
            extentTest.log(Status.PASS, "Tap on Login btn")

        } else {
            extentTest.log(Status.FAIL, "Tap on Login btn failed")
            Assert.fail()


        }

        accountServicesScreen = AccountServicesScreen(webDriver!!)

        if (accountServicesScreen.hasScreenLoaded()) {
            extentTest.log(Status.PASS, "Account services page loaded successfully.")
        } else {
            extentTest.log(Status.FAIL, "Page loaded failed")
            Assert.fail()

        }

        if (accountServicesScreen.clickOpenNewAccountLink()) {
            extentTest.log(Status.PASS, "Click on open new account link")
        } else {
            extentTest.log(Status.FAIL, "Click on open new account - Fail")

        }

        val actualSelectedAccountType = accountServicesScreen.getSelectedAccountTypeText()
        val expectedSelectedAccountType = "CHECKING"

        val isAccountSelected = accountServicesScreen.getSelectedAccountAttribute()?.toBoolean()

        if (actualSelectedAccountType == expectedSelectedAccountType && isAccountSelected == true) {
            extentTest.log(
                Status.PASS,
                "Checking account type is selected is [$isAccountSelected]. Actual: [$actualSelectedAccountType], Expected: [$expectedSelectedAccountType]"
            )
        } else {
            extentTest.log(
                Status.FAIL,
                "Checking account type is selected is [$isAccountSelected]. Actual: [$actualSelectedAccountType], Expected: [$expectedSelectedAccountType]"
            )

        }

        val actualFromAccountNumber = accountServicesScreen.getFromAccountNumber()
        val fromAccountNumberSelectedAttributeValue =
            accountServicesScreen.getFromAccountNumberSelectedAttribute()?.toBoolean()
        if (!actualFromAccountNumber.isNullOrBlank() && fromAccountNumberSelectedAttributeValue == true) {
            extentTest.log(
                Status.INFO,
                "Transferring from [$actualFromAccountNumber] and its selected status is [$fromAccountNumberSelectedAttributeValue]"
            )
        } else {
            extentTest.log(
                Status.FAIL,
                "Transferring from [$actualFromAccountNumber] and its selected status is [$fromAccountNumberSelectedAttributeValue]"
            )

        }

        if (accountServicesScreen.clickOpenNewAccountButton()) {
            extentTest.log(Status.PASS, "Click on open new account btn")
        } else {
            extentTest.log(Status.FAIL, "Click on open new account btn")
            Assert.fail()
        }


        val actualNewAccountNumber = accountServicesScreen.getNewAccountNumber()
        if (!actualNewAccountNumber.isNullOrBlank()) {
            extentTest.log(Status.INFO, "New account number: [$actualNewAccountNumber]")
        } else {
            extentTest.log(Status.FAIL, "New account number: [$actualNewAccountNumber]")
        }

        if (accountServicesScreen.clickNewAccountNumberLink()) {
            extentTest.log(Status.PASS, "Click on open new account number")
        } else {
            extentTest.log(Status.PASS, "Click on open new account number")
            Assert.fail()
        }

        val actualAccountDetailsAccountNumber = accountServicesScreen.getAccountDetailsAccountNumber()
        if (!actualAccountDetailsAccountNumber.isNullOrBlank() && actualAccountDetailsAccountNumber == actualNewAccountNumber) {
            extentTest.log(Status.INFO, "Account details account number matches: Actual: [$actualNewAccountNumber], Expected: [$actualAccountDetailsAccountNumber]")
        } else {
            extentTest.log(Status.FAIL, "Account details account number matches: [$actualNewAccountNumber], Expected: [$actualAccountDetailsAccountNumber]")

        }

        val actualAccountDetailsAccountBalance = accountServicesScreen.getAccountDetailsAccountBalance()
        val expectedAccountDetailsAccountBalance = "$100.00"
        if (!actualAccountDetailsAccountBalance.isNullOrBlank() && actualAccountDetailsAccountBalance == expectedAccountDetailsAccountBalance) {
            extentTest.log(
                Status.INFO,
                "Account details account balance matches: Actual: [$actualAccountDetailsAccountBalance], Expected: [$expectedAccountDetailsAccountBalance]"
            )
        } else {
            extentTest.log(
                Status.FAIL,
                "Account details account balance matches: [$actualAccountDetailsAccountBalance], Expected: [$expectedAccountDetailsAccountBalance]")
        }

        val actualAccountDetailsAccountAvailableBalance =
            accountServicesScreen.getAccountDetailsAccountAvailableBalance()
        val expectedAccountDetailsAccountAvailableBalance = "$100.00"
        if (!actualAccountDetailsAccountAvailableBalance.isNullOrBlank() && actualAccountDetailsAccountAvailableBalance == expectedAccountDetailsAccountAvailableBalance) {
            extentTest.log(
                Status.INFO,
                "Account details account balance matches: [$actualAccountDetailsAccountAvailableBalance], Expected: [$expectedAccountDetailsAccountAvailableBalance]"
            )
        } else {
            extentTest.log(
                Status.FAIL,
                "Account details account balance matches: [$actualAccountDetailsAccountAvailableBalance], Expected: [$expectedAccountDetailsAccountAvailableBalance]"
            )
        }


    }
}
