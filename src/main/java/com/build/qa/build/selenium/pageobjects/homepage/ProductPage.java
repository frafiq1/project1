package com.build.qa.build.selenium.pageobjects.homepage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.build.qa.build.selenium.pageobjects.BasePage;

public class ProductPage extends BasePage{

private By productPageWrapper;
	
	public ProductPage(WebDriver driver, Wait<WebDriver> wait) {
		super(driver, wait);
		productPageWrapper = By.cssSelector("#wrapper.plp");
	}
	
	public boolean onProductPage() {
		return wait.until(ExpectedConditions.presenceOfElementLocated(productPageWrapper)) != null;
	}
	
	
	
	@FindBy(xpath="//*[@id=\"wrapper\"]/main/div/div/div[2]/div/div[9]/ul/li")
	public static List<WebElement> listOfProducts;
	

	@FindBy(xpath="//button[@data-form='addToCartModalForm']")
	public static WebElement addToCartButton;
	
	
	@FindBy(xpath="//*[@id=\"wrapper\"]/header/div[9]/div/div[4]/a")
	public static WebElement cartPage;
	
	@FindBy(xpath="//*[@id=\"item-datas\"]/li/div[3]/div[2]/p")
	public static WebElement productIdCartpage;
	
	
	
	
	
	
}
