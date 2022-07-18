package com.build.qa.build.selenium.tests;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.build.qa.build.selenium.framework.BaseFramework;
import com.build.qa.build.selenium.pageobjects.BasePage;
import com.build.qa.build.selenium.pageobjects.homepage.HomePage;
import com.build.qa.build.selenium.pageobjects.homepage.ProductPage;



public class FergTest extends BaseFramework {

	/**
	 * Extremely basic test that outlines some basic
	 * functionality and page objects as well as assertJ
	 */
	@Ignore
	@Test
	public void navigateToHomePage() {
		driver.get(getConfiguration("HOMEPAGE"));
		HomePage homePage = new HomePage(driver, wait);

		softly.assertThat(homePage.onHomePage())
			.as("The website should load up with the Build.com desktop theme.")
			.isTrue();
	}

	/**
	 * Search for the Moen m6702bn from the search bar
	 * @assert: That the product page we land on is what is expected by checking the product brand and product id
	 * @difficulty Easy
	 */
	@Test
	public void searchForProductLandsOnCorrectProduct() {
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// TODO: Implement this test
	HomePage.searchField.sendKeys(getConfiguration("product"), Keys.ENTER);
	
	wait.until(ExpectedConditions.visibilityOf(HomePage.itemBrandName));
	
//	System.out.println(HomePage.itemBrandName.getText());
//	System.out.println(HomePage.productId.getText());
	
//	Assert.assertTrue(BasePage.itemBrandName.getText().contains(getConfiguration("productBrand")));
//	Assert.assertTrue(BasePage.productId.getText().contains(getConfiguration("productID")));

		softly.assertThat(HomePage.itemBrandName.getText()).containsIgnoringCase(getConfiguration("productBrand"));
		softly.assertThat(HomePage.productId.getText()).containsIgnoringCase(getConfiguration("productID"));

		
	}

	/**
	 * Go to the Bathroom Sinks category directly
	 * (https://www.ferguson.com/category/bathroom-plumbing/bathroom-faucets/bathroom-sink-faucets/_/N-zbq4i3)
	 * and add the second product on the search results (Category Drop) page to the cart.
	 * @throws InterruptedException 
	 * @assert: the product that is added to the cart is what is expected
	 * @difficulty Easy-Medium
	 */
	@Test
	public void addProductToCartFromCategoryDrop(){
		// TODO: Implement this test
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(getConfiguration("BathroomSinkPage"));
		
		wait.until(ExpectedConditions.visibilityOfAllElements(ProductPage.listOfProducts));
		
		
		String productPartNumber = null;
		
		for (int i = 0; i < ProductPage.listOfProducts.size(); i++) {
			
			
			if(i == 1) {
				
				driver.findElement(By.xpath("//*[@id=\"wrapper\"]/main/div/div/div[2]/div/div[9]/ul/li["+ i +"]/div[1]/div[8]")).click();
				productPartNumber =driver.findElement(By.xpath("//*[@id=\"wrapper\"]/main/div/div/div[2]/div/div[9]/ul/li["+ i +"]/div[1]/div[4]/div[1]")).getAttribute("data-fastcode");
				ProductPage.addToCartButton.click();
				
			}
		}
		
		System.out.println(productPartNumber);
		
		wait.until(ExpectedConditions.visibilityOf(ProductPage.cartPage)).click();
		
		wait.until(ExpectedConditions.invisibilityOf(ProductPage.productIdCartpage));
		
//		softly.assertThat(productPartNumber.containsIgnoringCase(ProductPage.productIdCartpage.getText());

	
	}

	/**
	 * Add two different finishes of a product (such as Moen m6702bn) to cart,
	 * change the quantity of each finish on the cart page
	 * @assert that the product and cart total update as expected when the quantity is changed
	 * @difficulty Medium-Hard
	 */
	@Ignore
	@Test
	public void addMultipleCartItemsAndChangeQuantity() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage.searchField.sendKeys(getConfiguration("product"), Keys.ENTER);
		driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();
		driver.findElement(By.xpath("//img[@title='Matte Black']")).click();
		driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();
		driver.findElement(By.cssSelector("a[class='js-cartitem-count cartitem-count cart-none-item'] span[class='count']")).click();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//input[@class='text-input qty-box js-qty jQ_quantityCode 7806811']"))).perform();
		driver.findElement(By.xpath("//input[@class='text-input qty-box js-qty jQ_quantityCode 7806811']")).click();
		action.moveToElement(driver.findElement(By.cssSelector("input[value='12'][name='updateQuantity']"))).perform();
		driver.findElement(By.xpath("//li[2]//div[3]//div[3]//div[2]//div[1]//div[1]//div[1]//div[2]")).click();
		
		
		
		//String productPartNumber = null;
		//productPartNumber =driver.findElement(By.xpath("//*[@id=\"wrapper\"]/main/div/div/div[2]/div/div[9]/ul/li["+ i +"]/div[1]/div[4]/div[1]")).getAttribute("data-fastcode");
		
		// TODO: Implement this test
	}

	/**
	 * Go to a category drop page (such as Bathroom Faucets) and narrow by
	 * at least two filters (facets), e.g: Finish=Chromes and Brand=Brizo
	 * @assert that the correct filters are being narrowed, and the result count
	 * is correct, such that each facet selection is narrowing the product count.
	 * @difficulty Hard
	 */
	@Ignore
	@Test
	public void facetNarrowBysResultInCorrectProductCounts() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(getConfiguration("HOMEPAGE"));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[normalize-space()='All Products']']"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//a[@class='mega-nav-dropdown-link w-inline-block hover']//div[contains(text(),'Bathroom')]"))).perform();
		driver.findElement(By.cssSelector("//div[contains(text(),'Bathroom Sink Faucets')]")).click();
		driver.findElement(By.xpath("//a[@data-dname='brand']")).click();
		driver.findElement(By.xpath("//div[@class='col-lg-3 col-md-3']//li[12]//label[1]//label[1]")).click();
		driver.findElement(By.xpath("//div[@class='col-lg-3 col-md-3']//ul[1]//li[12]//label[1]//label[1]")).click();
		
		// TODO: Implement this test//6176454655
	}
}
