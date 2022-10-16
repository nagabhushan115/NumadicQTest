package Test;



import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import io.github.bonigarcia.wdm.WebDriverManager;

public class NumdicQaEngineerTest {
	static WebDriver driver=null;
	@BeforeClass
	public void openBrowser() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test
	public void task() throws InterruptedException {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String expectedLink="https://numadic.com/careers/";
		driver.get("https://numadic.com/careers/");
		String actualLink=driver.getCurrentUrl();
		//Verifing the link
		Assert.assertEquals(expectedLink, actualLink,"This website application link is verified and found  incorrect link in web page");
		Reporter.log("This website application link is verified and found its correct link in web page");
//		if (expectedLink.contains(actualLink)) {
//			System.out.println("This website application link is verified and found its correct link in web page");
//		}else {
//			System.out.println("This website application link is verified and found  incorrect link in web page");
//
//		}

		//Verifing the Header text
		String expectedHeaderTextInPage="JOIN OUR CREW";

		WebElement text=driver.findElement(By.xpath("//h1[@class=\"animated fadeIn mb-md\"]"));
		String actualHeaderTextInPage = text.getText();
		Assert.assertEquals(expectedHeaderTextInPage, actualHeaderTextInPage,"This Header text is verified and found its Header Text is incorrect in web page");
		Reporter.log("This Header text is verified and found its Header Text is correct in web page");
		
//		if (expectedHeaderTextInPage.equals(actualHeaderTextInPage)) {
//			System.out.println("This Header text is verified and found its Header Text is correct in web page");
//		}else {
//			System.out.println("This Header text is verified and found its Header Text is incorrect in web page");
//		}

		//3rd task
		WebElement jobTypeDropdown = driver.findElement(By.id("job_type"));
		Select select = new Select(jobTypeDropdown);
		select.selectByVisibleText("Internship");
		String expectedMessageTextInPage="There are no available job positions that match your filters.";
		WebElement msgText=driver.findElement(By.xpath("//td[@class=\"dataTables_empty\"]"));
		String actualMessageTextInPage=msgText.getText();
		System.out.println(actualMessageTextInPage);
		Assert.assertEquals(expectedMessageTextInPage, actualMessageTextInPage,"This page is verified and found its showing available jobs in web page");
		Reporter.log("This page is verified and found its showing There are no available job positions that match your filters in web page");
		
//		if (expectedMessageTextInPage.equals(actualMessageTextInPage)) {
//			System.out.println("This page is verified and found its showing There are no available job positions that match your filters in web page");
//		}else {
//			System.out.println("This page is verified and found its showing available jobs in web page");
//		}

		//4th task
		String expectedUrlForQAEngineer="https://numadic.com/careers/qa-engineer.php";
		select.selectByVisibleText("Full time");
		WebElement QAEngineerOption=driver.findElement(By.xpath("//a[text()='QA Engineer']"));
		QAEngineerOption.click();
		String actualUrlForQAEngineer=driver.getCurrentUrl();
		Assert.assertEquals(expectedUrlForQAEngineer, actualUrlForQAEngineer,"This QA engineer url is verified and found its incorrect url in web page");
		Reporter.log("This QA engineer url is verified and found its correct url in web page");
		
//		if (expectedUrlForQAEngineer.equals(actualUrlForQAEngineer)) {
//			System.out.println("This QA engineer url is verified and found its correct url in web page");
//		}else {
//			System.out.println("This QA engineer url is verified and found its incorrect url in web page");
//		}

		//Verifying Apply here now button
		for (;;) {
			try {
				String expectedButtonTextInPage="Apply here now";
				WebElement button=driver.findElement(By.xpath("//a[@href=\"/careers#careersFormContainer\"]"));
				String actualButtonTextInPage=button.getText();
				if (expectedButtonTextInPage.equals(actualButtonTextInPage)) {
					System.out.println("This Button text is verified and found its correct button in web page");
					button.click();
					System.out.println("Apply here now button>>>>clicked");
					break;
				}else {
					System.out.println("This Button text is verified and found its incorrect button in web page");
				}
			}catch (Exception e) {
				JavascriptExecutor jse=(JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,500)");
			}
		}


		driver.navigate().back();
		driver.navigate().back();
		String expectedLinkInWebPage="https://numadic.com/careers/";
		String actualLinkInWebPage=driver.getCurrentUrl();
		
		//Verifing the URL
		Assert.assertEquals(expectedLink, actualLink,"This page URL is verified and not found  careers page");
		Reporter.log("This page URL is verified and found its careers page");

		
//		if (expectedLinkInWebPage.contains(actualLinkInWebPage)) {
//			System.out.println("This page URL is verified and found its careers page");
//		}else {
//			System.out.println("This page URL is verified and not found  careers page");
//
//		}
		
		WebElement QAEngineerLink=driver.findElement(By.xpath("//a[text()='QA Engineer']"));
		wait.until(ExpectedConditions.visibilityOf(QAEngineerLink));
		Actions actions = new Actions(driver);
		actions.moveToElement(QAEngineerLink).perform();
		WebElement applyButton = driver.findElement(By.xpath("//table[@id=\"job-posts-table\"]/tbody/tr[12]/td[5]/button[@class=\"btn btn-red-outline scroll-form\"]"));
		if (applyButton.isEnabled()) {
			applyButton.click();
			System.out.println("apply button>>>> clicked");
		}

		//verifing all fields visible or not
		List<WebElement> allTextFields = driver.findElements(By.xpath("//div[@id=\"step1\"]/descendant::input[@class=\"form-control\"]"));
		for (WebElement textField : allTextFields) {
			if (textField.isEnabled()) {
				System.out.println(textField.getAttribute("name")+" >>  text field is visible and working fine");
			}
			else {
				System.out.println("all text fields are not visible");
			}
		}

	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
