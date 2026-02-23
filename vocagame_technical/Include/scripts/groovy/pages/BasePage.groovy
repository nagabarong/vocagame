package pages

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.model.FailureHandling
import utils.OverlayHandler
import java.util.Arrays

public class BasePage {

    int DEFAULT_TIMEOUT = 15
    int SHORT_TIMEOUT = 2
    int RETRY = 2

    def click(TestObject object) {
        Exception lastException = null

        for (int i = 0; i <= RETRY; i++) {
            try {
                // 1. Handle overlay first (critical in headed mode)
                OverlayHandler.closeDialogIfPresent()

                // 2. Wait element present & visible & clickable
                waitPresent(object, DEFAULT_TIMEOUT)
                waitVisible(object, DEFAULT_TIMEOUT)
                waitClickable(object, SHORT_TIMEOUT)

                // 3. Scroll just in case (non-aggressive)
                WebUI.scrollToElement(object, SHORT_TIMEOUT)

                // 4. Click
                WebUI.click(object, FailureHandling.STOP_ON_FAILURE)
                KeywordUtil.logInfo("Clicked: " + object.getObjectId())
                return

            } catch (Exception e) {
                lastException = e
                KeywordUtil.logInfo("Retry click [" + (i+1) + "] for: " + object.getObjectId())

                // Retry overlay handling
                OverlayHandler.closeDialogIfPresent()
                WebUI.delay(0.3)

                // Final fallback: JS click
                if (i == RETRY) {
                    KeywordUtil.logInfo("Fallback to JS click: " + object.getObjectId())
                    def element = WebUI.findWebElement(object, DEFAULT_TIMEOUT)
                    WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(element))
                    return
                }
            }
        }

        KeywordUtil.markFailed(
            "Failed to click: " + object.getObjectId() +
            " | " + lastException?.getMessage()
        )
        throw lastException
    }

    def setText(TestObject object, String value) {
        try {
            OverlayHandler.closeDialogIfPresent()
            waitPresent(object, DEFAULT_TIMEOUT)
            waitVisible(object, DEFAULT_TIMEOUT)
            WebUI.scrollToElement(object, SHORT_TIMEOUT)
            WebUI.setText(object, value, FailureHandling.STOP_ON_FAILURE)
            KeywordUtil.logInfo("Set text: " + object.getObjectId())
        } catch (Exception e) {
            KeywordUtil.markFailed(
                "Failed to set text: " + object.getObjectId() +
                " | " + e.getMessage()
            )
            throw e
        }
    }

    def verifyVisible(TestObject object, int timeout = DEFAULT_TIMEOUT) {
        waitPresent(object, timeout)
        waitVisible(object, timeout)
        WebUI.verifyElementVisible(object, FailureHandling.STOP_ON_FAILURE) // no timeout here
    }

    private def waitPresent(TestObject object, int timeout = DEFAULT_TIMEOUT) {
        WebUI.waitForElementPresent(object, timeout, FailureHandling.STOP_ON_FAILURE)
    }

    private def waitVisible(TestObject object, int timeout = DEFAULT_TIMEOUT) {
        WebUI.waitForElementVisible(object, timeout, FailureHandling.STOP_ON_FAILURE)
    }

    private def waitClickable(TestObject object, int timeout = DEFAULT_TIMEOUT) {
        WebUI.waitForElementClickable(object, timeout, FailureHandling.STOP_ON_FAILURE)
    }

    def waitDisappear(TestObject object, int timeout = DEFAULT_TIMEOUT) {
        WebUI.waitForElementNotVisible(object, timeout, FailureHandling.OPTIONAL)
    }

    def waitForPageReady() {
        try {
            WebUI.waitForPageLoad(DEFAULT_TIMEOUT)
            KeywordUtil.logInfo("Page load check passed.")
        } catch (Exception e) {
            KeywordUtil.logInfo("Page ready check skipped: " + e.getMessage())
        }
    }
}