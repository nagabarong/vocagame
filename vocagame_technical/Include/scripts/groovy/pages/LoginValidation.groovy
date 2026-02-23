package pages
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

import pages.BasePage


class LoginValidation extends BasePage {

    /**
     * Helper: Generic element validation
     */
    private void validateElement(TestObject object, int timeout, String successMsg, String errorMsg) {
        boolean visible = WebUI.waitForElementVisible(object, timeout)

        if (!visible) {
            WebUI.takeScreenshot()
            KeywordUtil.markFailed(errorMsg)
        }

        KeywordUtil.logInfo(successMsg)
    }

    /**
     * Validate login success by checking dashboard element
     */
    def verifyLoginSuccess() {
        validateElement(
            findTestObject('Dashboard/konfirmasi_login'),
            20,
            "Login successfully validated.",
            "Login validation failed: Dashboard confirmation element not found."
        )
        WebUI.takeScreenshot()
    }

    /**
     * Wrong password validation
     */
    def verifyWrongPassword() {
        validateElement(
            findTestObject('Login/Validasi_Login_Pw'),
            10,
            "Wrong password validation passed.",
            "Validation failed: Wrong password error message not detected."
        )
    }

    /**
     * Empty field validation
     */
    def verifyEmptyField() {
        validateElement(
            findTestObject('Login/Validasi_email'),
            10,
            "Empty field validation passed.",
            "Validation failed: Empty field message not found."
        )
    }

    /**
     * Invalid email format validation
     */
    def verifyInvalidEmailFormat() {
        validateElement(
            findTestObject('Login/Validasi_Format_Email'),
            10,
            "Invalid email format validation passed.",
            "Validation failed: Invalid email format message not detected."
        )
    }
	
	/**
	 * Email not register validation
	 */
	def verifyUserNotRegistered() {
		validateElement(
			findTestObject('login/validasi_Email_NotRegisterd'),
			10,
			"Email is not registered passed",
			"validation failed: Email not register not detected."
			)
	}
	
	/**
	 * Invalid email locked validation
	 */
	def verifyAccountLocked() {
		 validateElement(
			findTestObject('login/validasi_Email_locked'),
			10,
			"Email is locked passed",
			"validation failed: Email locked is not detected."
			)
	}
}