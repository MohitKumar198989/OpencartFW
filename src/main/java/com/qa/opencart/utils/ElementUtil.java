package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exception.FrameworkException;

import io.qameta.allure.Step;

public class ElementUtil {
	
	private WebDriver driver;  
	
	/**
	 * This is constructor of this class
	 * @param driver
	 */
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
	}
	/**
	 * This method select the element through its element locator
	 * 
	 * @param locatorType
	 * @param locatorValue
	 * @return
	 */
	public By getBy(String locatorType, String locatorValue ) {
		By by= null;
		
		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by=By.id(locatorValue);
			break;
			
		case "name":
			by=By.name(locatorValue);
			break;
			
		case "class":
			by=By.className(locatorValue);
			break;
			
		case "xpath":
			by=By.xpath(locatorValue);
			break;
			
		case "css":
			by=By.cssSelector(locatorValue);
			break;
			
		case "linktext":
			by=By.linkText(locatorValue);
			break;
			
		case "partiallinktext":
			by=By.partialLinkText(locatorValue);
			break;
			
		case "tagName":
			by=By.tagName(locatorValue);
			break;
			
		default:
			System.out.println("wronge locator is passed ..."+locatorType);
			throw new FrameworkException("WRONG LOCATOR TYPE");
		}
		return by;
	}

	//locatorType=id , locator Value = "input-email" value ="tom@gmail.com"
//	public void doSendKeys(String locatorType, String locatorValue, String value) {
//		getElement(getBy(locatorType, locatorValue)).sendKeys(value);
//	}
	/**
	 * This method is used to enter the value
	 * @param locatorType
	 * @param locatorValue
	 * @param value
	 */
	
	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);
	}
	
	/**
	 * This method used to enter the value
	 * @param locator
	 * @param value
	 */
	@Step("entering value {1} to element: {0}")
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	/**
	 * This method is used to click on the element 
	 * @param locator
	 */
	@Step("clicking on element : {0}")
	public void doClick(By locator) {
		getElement(locator).click();
	}
	/**
	 * This method is used to click on the element
	 * @param locatorType
	 * @param locatorValue
	 */
	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}
	/**
	 * This method is to get the text of the web element.
	 * @param locator
	 * @return
	 */
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
		
	}
	/**
	 * This method is to get the text of the web element.
	 * @param locatorType
	 * @param locatorValue
	 * @return
	 */
	public String doElementGetText(String locatorType, String locatorValue) {
		return getElement(locatorType, locatorValue).getText();
		
	}
	
	

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public WebElement getElement(String locatortype, String locatorValue) {
		return driver.findElement(getBy(locatortype, locatorValue));
	}
	
	public String doGetElementAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	
	
	//*************Select drop down Utils***********//
	
	private Select createSelect(By locator) {
		Select select=new Select(getElement(locator));
		return select;
	}
	
	public void  doSelectDropDownByIndex(By locator, int index) {
		//Select select = new Select(getElement(locator));
		createSelect(locator).selectByIndex(index);
		// select.selectByIndex(index);
		
	}
	
	public void  doSelectDropDownByVisibleTest(By locator, String visibleText) {
		//Select select = new Select(getElement(locator));
		createSelect(locator).selectByVisibleText(visibleText);;
		
	}
	
	public void  doSelectDropDownByValue(By locator, String value) {
		//Select select = new Select(getElement(locator));
		createSelect(locator).selectByValue(value);;
		
	}
	
	public int getDropDownOptionsCount(By locator) {
		//Select select = new Select(getElement(locator));
		return createSelect(locator).getOptions().size();
		
	}
	
	public List<String> getDropDownOptions(By locator) {
		//Select select = new Select(getElement(locator));
		
		List<WebElement>optionsList = createSelect(locator).getOptions();
		List<String> optionsTextList= new ArrayList<String>();
		for(WebElement e:optionsList  ) {
			String text= e.getText();  
			//System.out.println(text);
			optionsTextList.add(text);
				
		}
		return optionsTextList;
		
	}	
	
	public void selectDropDownOption(By locator, String dropDownValue) {
		//Select select = new Select(getElement(locator));
		
		List<WebElement>optionsList = createSelect(locator).getOptions();
		
		System.out.println(optionsList.size());
		
		for(WebElement e:optionsList  ) {
			String text= e.getText();  
			System.out.println(text);
			if (text.equals(dropDownValue)) {
				e.click(); 
				break;
			}
		}
	}
	
	public void selectDropDownValue(By locator , String value) {
		List<WebElement> optionsList= getElements(locator);
		for (WebElement e: optionsList) {
			String text= e.getText();
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
		
	}
	
	public boolean isDropDownMultipleVaules(By locator) {
		//Select select = new Select(getElement(locator));
		return createSelect(locator).isMultiple()?true:false;
	}
	 /**
	  * This method is used to select the value from the drop down . It can selet
	  * 1.single selection
	  * 2.Multiple selection 
	  * 3.All selection : please pass "all" as a value to select all the values
	  * @param locator
	  * @param values
	  */
	public  void selectDropDownMultipleValues(By locator,By optionLocator, String... values) {
		//Select select = new Select(getElement(locator));
		if (isDropDownMultipleVaules(locator)) {
			
			if (values[0].equalsIgnoreCase("all")){
//				List<WebElement> options = select.getOptions();
//				select.selectByVisibleText(value);
				
				List<WebElement> options=driver.findElements(optionLocator);
				for(WebElement e: options ) {
					e.click();
				}
				 
			}
			else {
				for (String value: values) {
					
					createSelect(locator).selectByVisibleText(value);
				}
			}
		

			
		}
		
	
	}
	
	//******************Actions Utils ************//
	
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}
	
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}
	
	
	public void twoLevelMenu(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
		Actions act =new Actions(driver);
		act.moveToElement(getElement(parentMenuLocator)).build().perform();
		Thread.sleep(1000);
		doClick(childMenuLocator);
	}
			
	public  void fourLevelMenuHandle(By parentMenuLocator,By firstchildMenuLocator,By secondChildMenuLocator,By thirdChildMenuLocator ) throws InterruptedException {
		
		Actions act = new Actions(driver);
		
		doClick(parentMenuLocator);
		
		Thread.sleep(1000);
		
		act.moveToElement(getElement(firstchildMenuLocator)).build().perform();
		
		 
		Thread.sleep(1000);
		
		act.moveToElement(getElement(secondChildMenuLocator)).build().perform();
		
		Thread.sleep(1000);
		
		doClick(thirdChildMenuLocator);
		
	}
	
	public void doActionsSendKeysWithPause(By locator,String value) {

		Actions act = new Actions(driver);
		
		char val[]=value.toCharArray();
		
		for(char c: val) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(500).build().perform();
		}
		
	}
	
	//************** Wait Utils **************//
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * This does not necessarily mean that the element is visible.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForPersenceOfElement(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	    return  wait.until(ExpectedConditions.presenceOfElementLocated(locator));	
	    
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * This does not necessarily mean that the element is visible.
	 * @param locator
	 * @param timeout
	 * @param intervalTime
	 * @return
	 */
	
	public WebElement waitForPersenceOfElement(By locator,int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofSeconds(intervalTime));
	    return  wait.until(ExpectedConditions.presenceOfElementLocated(locator));	
	    
	}
	
	
	/**
	 *  An expectation for checking that an element is present on the DOM of a page and visible.
	 *  Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	@Step("waiting for element: {0} with timeout {1}")
	public WebElement waitForVisibilityOfElement(By locator,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	    return  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
	    
	}
	
	public void doClickwithWait(By locator, int timout) {
		waitForVisibilityOfElement(locator, timout).click();
	}
	
	
 
	public void doSendKeysWait(By locator, String value, int timout) {
		waitForVisibilityOfElement(locator, timout).sendKeys(value);;
	}
	
	/**
	 * An expectation for checking that all elements present on the web page that match the locator are visible. 
	 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForVisibilityOfElements(By locator,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	    return  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));	
	    
	}
	
	/**
	 * An expectation for checking that there is at least one element present on a web page.
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForPresenceOfAllElements(By locator,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	    return  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));	
	    
	}
	
	
	public String waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
			}	
		}
		catch (TimeoutException e){
			System.out.println(titleFraction +"title value is not present....");
			e.printStackTrace();
 		}
		return null;
		
	}
	
	@Step("waiting for the page title : {0} and titmeout: {1}")
	public String waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		if (wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
			}	
		}
		catch (TimeoutException e){
			System.out.println(title +" title value is not present....");
			e.printStackTrace();
 		}
		return driver.getTitle();
		
	}
	
	@Step("waiting for url : {0} and timeout{1}")
	public String waitForURLContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
			}	
		}
		catch (TimeoutException e){
			System.out.println(urlFraction +"url value is not present....");
			e.printStackTrace();
 		}
		return null;
		
	}
	
	public String waitForURLToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		if (wait.until(ExpectedConditions.urlToBe(url))) {
			return driver.getCurrentUrl();
			}	
		}
		catch (TimeoutException e){
			System.out.println(url +"url value is not present....");
			e.printStackTrace();
 		}
		return null;
		
	}
	
	
	public Alert waitForJSAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptJSAlert(int timeOut) {
		waitForJSAlert(timeOut).accept();;
	}
	
	public void dismissJSAlert(int timeOut) {
		waitForJSAlert(timeOut).dismiss();
	}
	
	public String getJSAlertText(int timeOut) {
		return waitForJSAlert(timeOut).getText();
	}
	
	public void enterValueOnJSAlertText(int timeOut, String value) {
		waitForJSAlert(timeOut).sendKeys(value);
	}
	
	public void  waitForFrameByLocator(By framelocator,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));	
	}
	
	public void  waitForFrameByIndex(int frameIndex,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));	
	}
	
	public void  waitForFrameByIDOrName(String IDOrName,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));	
	}
	
	public void  waitForFrameByElement(String frameElement,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));	
	}
	
	public boolean checkNewWindowExist(int timeOut, int expectedNumberOfWindow) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindow))) {
				return true;
			}
		} catch (TimeoutException e) {
			System.out.println("number of windows are not same...");
		}
		return false;
	}
	
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeOut
	 */
	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}catch (TimeoutException e) {
			System.out.println("element is not clickable or enabled...");
		}
	}
	
	public WebElement waitForElementWithFluentWait(By locator, int timeOut, int intervalTime) {
		 Wait<WebDriver> wait =new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(intervalTime))
					.withMessage("--time out is done ...element is  not found...")
					.ignoring(org.openqa.selenium.NoSuchElementException.class)
					.ignoring(ElementNotInteractableException.class);
					
		 return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	public void waitForFrameWithFluentWait(String frameIDOrName, int timeOut, int intervalTime) {
		 Wait<WebDriver> wait =new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(intervalTime))
					.withMessage("--time out is done ...element is  not found...")
					.ignoring(NoSuchFrameException.class);
					
		 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIDOrName));
	}
	
	public void waitForJSAlertWithFluentWait( int timeOut, int intervalTime) {
		 Wait<WebDriver> wait =new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(intervalTime))
					.withMessage("--time out is done ...element is  not found...")
					.ignoring(NoAlertPresentException.class);
					
		 wait.until(ExpectedConditions.alertIsPresent());
	}
	
	//****************Custom Wait ********************//
	
	public WebElement retryingElement(By locator, int timeOut) {
		WebElement element = null;
		int attempts=0;
		
		while (attempts<timeOut) {
			try {
				element= getElement(locator);
				System.out.println("element is found ..."+locator+ "in attempts "+attempts);
				break;
			}
			catch(NoSuchElementException e) {
				System.out.println("element is not found..."+locator+"int attempts "+attempts);
				try {
					Thread.sleep(500);//default polling time = 500ms
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		
		if (element==null) {
			System.out.println("element is not found.... tried for "+timeOut+" with the interval of "+500+"milisecond");
			throw new FrameworkException("No Such Element");
		}
		return element;
	}
	
	
	public WebElement retryingElement(By locator, int timeOut, int intervalTime) {
		WebElement element = null;
		int attempts=0;
		
		while (attempts<timeOut) {
			try {
				element= getElement(locator);
				System.out.println("element is found ..."+locator+ "in attempts "+attempts);
				break;
			}
			catch(NoSuchElementException e) {
				System.out.println("element is not found..."+locator+"int attempts "+attempts);
				try {
					Thread.sleep(intervalTime);// custom polling time
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		
		if (element==null) {
			System.out.println("element is not found.... tried for "+timeOut+" with the interval of "+500+"milisecond");
			throw new FrameworkException("No Such Element");
		}
		return element;
	}
	
	public Boolean isPageLoaded(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut)) ;
		String flag=wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'")).toString();
		return Boolean.parseBoolean(flag);
	}
	
	
}
