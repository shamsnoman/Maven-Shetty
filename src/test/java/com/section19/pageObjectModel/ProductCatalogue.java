package com.section19.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.section19.abstractComponents.AbstractComponent;

public class ProductCatalogue  extends AbstractComponent {
	public WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy
	(css=".mb-3")List<WebElement> products;
	
	@FindBy
	(css=".mb-3")WebElement product;
	
	@FindBy
	(id = "toast-container")WebElement toastMessage;
	@FindBy
	(css = ".ng-animating")WebElement spinner;
	
	By addToCart = By.cssSelector(".card-body>button:nth-of-type(2)");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(product);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b"))
				.getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}

}
