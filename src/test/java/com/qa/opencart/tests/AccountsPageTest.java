package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class AccountsPageTest extends BaseTest {

	@Description("Login to the app")
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));;
	}
	
	@Description("Verify the Account Page title")
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccPageTitle(),AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Description("Verify the Account Page URL")
	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accPage.getAccPageURL().contains(AppConstants.ACC_PAGE_URL_FRACTION));
	}
	
	@Description("Verify the Logout link exist")
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogutLinkExist());
	}
	
	@Description("Verify the SearchFieldExist on AccountPage")
	@Test
	public void isSearchFieldExistTest() {
		Assert.assertTrue(accPage.isSearchFieldExist());
	}
	
	@Description("Verify the Account page headers counts")
	@Test
	public void accPageHeadersCountTest() {
		List<String> actAccPageHeadersList = accPage.getAccountsHeaders();
		System.out.println(actAccPageHeadersList);
		Assert.assertEquals(actAccPageHeadersList.size() , AppConstants.ACC_PAGE_HEADER_COUNT);
	}
	
	@Description("Verify the Account page header list")
	@Test
	public void accPageHeadersTest() {
		List<String> actAccPageHeadersList = accPage.getAccountsHeaders();
		System.out.println(actAccPageHeadersList);
		Assert.assertEquals(actAccPageHeadersList, AppConstants.ACCOUNT_PAGE_HEADER_LIST);
	}
	
//	@Test
//	public void accPageHeadersListCompareTest() {
//		List<String> actAccPageHeadersList = accPage.getAccountsHeaders();
//		
//		Collections.sort(actAccPageHeadersList);
//		System.out.println(actAccPageHeadersList);
//		
//		List<String> expectedAccPageHeadersList =AppConstants.ACCOUNT_PAGE_HEADER_LIST;
//		Collections.sort(expectedAccPageHeadersList);
//		Assert.assertEquals(actAccPageHeadersList, expectedAccPageHeadersList);
//	}
	
	@Test
	public void searchTest() {
		searchResultsPage  = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actProductHeader = productInfoPage.getProductHeaderName();
		Assert.assertEquals(actProductHeader, "MacBook Pro");
		
	}
	
	
	
	
}
