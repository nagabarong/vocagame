package pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil

import pages.BasePage

class LoginValidation extends BasePage {

	/**
	 * Generic element validator
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
	 * Validate login success
	 */
	def verifyLoginSuccess() {
		validateElement(
			findTestObject('dashboard/konfirmasi_login'),
			20,
			"Login successfully validated.",
			"Validation failed: Dashboard confirmation element not found."
		)
	}

	/**
	 * Wrong password validation
	 */
	def verifyWrongPassword() {
		validateElement(
			findTestObject('login/Validasi_login_password'),
			10,
			"Wrong password validation passed.",
			"Validation failed: Wrong password message not detected."
		)
	}

	/**
	 * Empty field validation
	 */
	def verifyEmptyField() {
		validateElement(
			findTestObject('login/validasi_email_kosong'),
			10,
			"Empty field validation passed.",
			"Validation failed: Empty field message not detected."
		)
	}

	/**
	 * Invalid email format validation
	 */
	def verifyInvalidEmailFormat() {
		validateElement(
			findTestObject('login/validasi_format_email'),
			10,
			"Invalid email format validation passed.",
			"Validation failed: Invalid email format message not detected."
		)
	}

	/**
	 * Email not registered validation
	 */
	def verifyUserNotRegistered() {
		validateElement(
			findTestObject('login/validasi_email_not_registered'),
			10,
			"Unregistered email validation passed.",
			"Validation failed: Unregistered email message not detected."
		)
	}

	/**
	 * Account locked validation
	 */
	def verifyAccountLocked() {
		validateElement(
			findTestObject('login/validasi_email_locked'),
			10,
			"Account locked validation passed.",
			"Validation failed: Account locked message not detected."
		)
	}
}