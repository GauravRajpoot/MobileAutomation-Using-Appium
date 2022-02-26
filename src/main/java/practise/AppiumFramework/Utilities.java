package practise.AppiumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static java.time.Duration.ofSeconds;

import java.net.MalformedURLException;
import java.net.URL;

public class Utilities {
	AndroidDriver<AndroidElement> driver;

	public Utilities(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
	}

	public void scrollToText(String text) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));");
	}

	public void tapLongPress() {
		// tap
		TouchAction t = new TouchAction(driver);
		WebElement expandList = driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");
		t.tap(tapOptions().withElement(element(expandList))).perform();

		// Long press

		WebElement pn = driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
		t.longPress(longPressOptions().withElement(element(pn)).withDuration(ofSeconds(2))).release().perform();
	}

	public void jsScrippt() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)", "");
	}

	public void swipe() {
		TouchAction t = new TouchAction(driver);
		// long press //on element// 2 sec// move to another element and you release
		WebElement first = driver.findElementByXPath("//*[@content-desc='15']");
		WebElement second = driver.findElementByXPath("//*[@content-desc='45']");
		t.longPress(longPressOptions().withElement(element(first)).withDuration(ofSeconds(2))).moveTo(element(second))
				.release().perform();
	}

	public void swipeToDesiredElement() {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
		// if above code of swipe is not working try below code
		// driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new
		// UiSelector().scrollable(true).instance(0)).scrollIntoView(new
		// UiSelector().textMatches(\"" + containedText + "\").instance(0))"));
		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));
	}

	public void longPress() {
		TouchAction t = new TouchAction(driver);
		WebElement expandList = driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");
		t.tap(tapOptions().withElement(element(expandList))).perform();
	}

	public void getToastMessage() {
		String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
	}

	public class Xpath {
		public void xpathExample() {
			// driver.findElementByAndroidUIAutomator("attribute("value")")
			driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
			// Validate clickable feature for all options
			driver.findElementsByAndroidUIAutomator("new UiSelector().property(value)");

			System.out.println(driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size());

			/*
			 * Xpath Syntax //tagName[@attribute='value']
			 */

			driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
			driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
			driver.findElementById("android:id/checkbox").click();
			driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
			driver.findElementByClassName("android.widget.EditText").sendKeys("hello");
			driver.findElementsByClassName("android.widget.Button").get(1).click();
		}

		public void desiredCapablityForMobileBrowser() throws MalformedURLException {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "");
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
					capabilities);
		}

		public void moveToElementAndAddToCart() {
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));
			int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
			for (int i = 0; i < count; i++) {
				String text = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i)
						.getText();
				if (text.equalsIgnoreCase("Jordan 6 Rings")) {
					driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
					break;
				}
			}
			driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
			String lastpageText = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
			Assert.assertEquals("Jordan 6 Rings", lastpageText);
		}
	}
}
