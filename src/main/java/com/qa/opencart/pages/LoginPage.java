package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By locator: OR
	private By userName = By.id("input-email");
	private By passWord = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	
	
	private By registerLink = By.linkText("Register"); 
	
	
	//page const...
	/**
	 * 
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil =new ElementUtil(this.driver);
	}
	/**
	 * this method getting login page title
	 * @return title
	 */
	//page/actions/methods:
	@Step("getting login page title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAUTT_WAIT);
		System.out.println("login page title:"+title);
		return title;

	}
	
	/**
	 * this method getting login page url
	 * @return url
	 */
	
	@Step("getting login page url")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAUTT_WAIT);
		System.out.println("login page title:"+url);
		return url;

	}
	/**
	 * this method return true if forgot pwd link exist
	 * @return Boolean
	 */
	@Step("checking forgot pwd link exist")
	public boolean isForgotPwdLinkExist() {
		
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();

	}
	/**
	 * this method return true if app logo exist
	 * 
	 * @return Boolean
	 */
	@Step("checking logo exist")
	public boolean isLogoExist() {
		
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	
	}
	/**
	 * this method login to the application
	 * @param username
	 * @param pwd
	 * @return AccountsPage
	 */
	
	@Step("username is : {0} and password {1} ")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("cred are: " +username+ ":"+pwd);
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAUTT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(passWord, pwd);
		eleUtil.doClick(loginBtn);
		System.out.println("user is logged in");
		
		return new AccountsPage(driver);
		
	}
	/**
	 * This method is to navigating to register page 
	 * @return RegisterPage
	 */
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAUTT_WAIT).click();
		return new RegisterPage(driver);
	}
	
	
}
