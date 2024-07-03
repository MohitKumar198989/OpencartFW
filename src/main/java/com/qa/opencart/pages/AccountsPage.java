package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accHeaders = By.cssSelector("div#content>h2");
	
	
	
	// page const...
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(this.driver);
		}
	
	//page actions :
	@Step("get account page title")
	public String getAccPageTitle()
	{
		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_DEFAUTT_WAIT);
		System.out.println("Acc page title:" +title);
		return title;
	}
	
	@Step("get account page URL")
	public String getAccPageURL()
	{
		String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAUTT_WAIT);
		System.out.println("Acc page url:"+url);
		return url;
	}
	@Step ("get info of logout link available on the Account Page")
	public boolean isLogutLinkExist()
	{
		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}
	
	@Step("logout for the application")
	public void logout() {
		if(isLogutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}
	@Step ("Search field is display then retrun true")
	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(search,AppConstants.SHORT_DEFAUTT_WAIT).isDisplayed();
	}
	
	@Step ("get Account header")
	public List<String> getAccountsHeaders() {
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElements(accHeaders,AppConstants.MEDIUM_DEFAUTT_WAIT);
		List<String> headersValList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	
	//Data provider used, to clear the previous text .clear() is used 
	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAUTT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAUTT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);//TDD
	}
	
}
