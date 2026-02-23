package pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject

class LoginPage extends BasePage {

    private TestObject getBtnLoginMenu() {
        return findTestObject('Dashboard/button_Masuk')
    }

    private TestObject getFieldEmail() {
        return findTestObject('Login/Form_field_noHP_Email')
    }

    private TestObject getBtnMasukAkun() {
        return findTestObject('Login/button_masuk_akun')
    }

    private TestObject getFieldPassword() {
        return findTestObject('Login/input_show_password')
    }

    def clickLoginMenu() {
        click(getBtnLoginMenu())
    }

    def inputEmail(String email) {
        setText(getFieldEmail(), email)
    }

    def clickMasukAkun() {
        click(getBtnMasukAkun())
        waitReady(getFieldPassword())
    }

    def inputPassword(String password) {
        setText(getFieldPassword(), password)
    }

    def clickSubmit() {
        click(getBtnMasukAkun())
    }
}