package steps
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

import io.cucumber.java.en.*
import io.cucumber.java.Before
import io.cucumber.java.After

import pages.LoginPage
import steps.Hooks
import pages.BasePage
import pages.LoginValidation

class LoginFlow {
	
	LoginPage loginPage = new LoginPage()
	LoginValidation loginValidation = new LoginValidation()

	@Given("User open application")
	def openApp() {
		// Already handled by Hooks
	}

	@When("User navigate to login page")
	def navigateLogin() {
		loginPage.clickLoginMenu()
	}

	@When("User input email {string}")
	def inputEmail(String email) {
		loginPage.inputEmail(email)
	}
	
	@And("User click continue button")
	def clickContinue() {
		loginPage.clickMasukAkun()
	}

	@When("User input password {string}")
	def inputPassword(String password) {
		loginPage.inputPassword(password)
	}

	@When("User click login button")
	def clickLogin() {
		loginPage.clickSubmit()
	}

	@Then("Login result should be {string}")
	def verifyResult(String result) {
    switch(result) {
        case "success":
            loginValidation.verifyLoginSuccess()
            break
        
        case "wrong_pass":
            loginValidation.verifyWrongPassword()
            break
        
        case "empty_field":
            loginValidation.verifyEmptyField()
            break
        
        case "invalid_email":
            loginValidation.verifyInvalidEmailFormat()
            break
        
        case "not_registered":
            loginValidation.verifyUserNotRegistered()
            break
        
        case "locked":
            loginValidation.verifyAccountLocked()
            break
        
        default:
            throw new Exception("⚠ Unknown expected result: " + result)
    }
}
 
}