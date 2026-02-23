package steps

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

import pages.LoginPage
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