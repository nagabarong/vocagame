package steps

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import io.cucumber.java.Before
import io.cucumber.java.After

class Hooks {

    @Before
	public void setUp() {
    WebUI.openBrowser('')
	WebUI.maximizeWindow()
    //WebUI.setViewPortSize(1920, 1080)
    WebUI.navigateToUrl(GlobalVariable.baseUrl)
    WebUI.waitForPageLoad(15)
}

    @After
    public void tearDown() {
        WebUI.closeBrowser()
    }
}