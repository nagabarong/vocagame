package utils

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import org.openqa.selenium.Keys

public class OverlayHandler {

    private static final int QUICK_TIMEOUT = 1

    static void closeDialogIfPresent() {
        try {
            // Define overlay TestObject dynamically
            TestObject dialog = new TestObject('PopupOverlay')
            dialog.addProperty('xpath', com.kms.katalon.core.testobject.ConditionType.EQUALS,
                "//div[@role='dialog' and @data-state='open']")

            // Quick check if overlay exists
            boolean isPresent = WebUI.verifyElementPresent(dialog, QUICK_TIMEOUT, FailureHandling.OPTIONAL)
            if (!isPresent) return

            WebUI.waitForElementVisible(dialog, QUICK_TIMEOUT, FailureHandling.OPTIONAL)

            KeywordUtil.logInfo("Popup overlay detected, handling safely...")

            // Strategy 1: Send ESC to the page (null works for the document)
            WebUI.sendKeys(null, Keys.chord(Keys.ESCAPE))
            WebUI.delay(0.3)

            // Re-check if overlay is gone
            boolean stillVisible = WebUI.verifyElementPresent(dialog, 1, FailureHandling.OPTIONAL)
            if (stillVisible) {
                KeywordUtil.logInfo("ESC did not close overlay, using JS fallback")
                WebUI.executeJavaScript("document.body.click();", null)
                WebUI.delay(0.3)
            }

            KeywordUtil.logInfo("Overlay handled successfully.")

        } catch (Exception e) {
            // Never fail the test due to overlay
            KeywordUtil.logInfo("Overlay handler skipped: " + e.getMessage())
        }
    }
}