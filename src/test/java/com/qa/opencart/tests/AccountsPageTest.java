package com.qa.opencart.tests;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));;
	}
	
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccPageTitle(),AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accPage.getAccPageURL().contains(AppConstants.ACC_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogutLinkExist());
	}
	
	@Test
	public void isSearchFieldExistTest() {
		Assert.assertTrue(accPage.isSearchFieldExist());
	}
	@Test
	public void accPageHeadersCountTest() {
		List<String> actAccPageHeadersList = accPage.getAccountsHeaders();
		System.out.println(actAccPageHeadersList);
		Assert.assertEquals(actAccPageHeadersList.size() , AppConstants.ACC_PAGE_HEADER_COUNT);
	}
	
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
