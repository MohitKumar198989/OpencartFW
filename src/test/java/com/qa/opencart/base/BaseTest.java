package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
@Listeners(TestAllureListener.class)

public class BaseTest {
	
	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	protected SoftAssert softAssert;
	
	@Parameters({"browser","browserversion","testname"})
	@BeforeTest
	/**
	 * This method initialize the prop class.
	 * @param browserName
	 * @param browserVersion
	 * @param testName
	 */
	public void setup(String browserName, String browserVersion, String testName) {
		df=new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
		}
		
		driver= df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	/**
	 * This method is used for close/quit the web browser
	 */
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
