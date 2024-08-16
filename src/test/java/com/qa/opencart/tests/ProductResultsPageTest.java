package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;

public class ProductResultsPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));;
	}
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"iMac", "iMac", 3},
			{"Samsung", "Samsung SyncMaster 941BW", 1}

		};
	}
	
	@DataProvider
	public Object[][] getSearchExcelTestData() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
	}
	

	@Description("Verify the Product image count")
	@Test(dataProvider = "getSearchExcelTestData")
	public void productImagesTest(String searchKey, String productName, String imageCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		Assert.assertEquals(String.valueOf(productInfoPage.getProductImagesCount()), imageCount);
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("MacBook"); 
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDetailsMap = productInfoPage.getproductDetails();

//		
//		Assert.assertEquals(productDetailsMap.get("Brand"),"Apple");
//		Assert.assertEquals(productDetailsMap.get("Availability"),"In Stock");
//		Assert.assertEquals(productDetailsMap.get("Product Code"),"Product 18");
//		Assert.assertEquals(productDetailsMap.get("Reward Points"),"800");
//		
//		Assert.assertEquals(productDetailsMap.get("price"),"$2,000.00");
//		Assert.assertEquals(productDetailsMap.get("extaxprice"),"$2,000.00");
		
		
		
		softAssert.assertEquals(productDetailsMap.get("Brand"),"Apple");
		softAssert.assertEquals(productDetailsMap.get("Availability"),"In Stock");
		softAssert.assertEquals(productDetailsMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"),"800");
		
		softAssert.assertEquals(productDetailsMap.get("price"),"$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("extaxprice"),"$2,000.00");

		softAssert.assertAll(); // mandatory to write

		
	}
	
}
