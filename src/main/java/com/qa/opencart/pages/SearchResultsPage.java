package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	


	// page const...

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	/**
	 * 
	 * @param productName
	 * @return
	 */
	@Step("Select Product")
	public ProductInfoPage selectProduct(String productName) {
		eleUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstants.MEDIUM_DEFAUTT_WAIT).click();
		return new ProductInfoPage(driver); 
	}
}
