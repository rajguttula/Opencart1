package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MyAcountPage extends BasePage {

	public MyAcountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']") WebElement msgMyAccountHeading;
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']") WebElement lnkLogout;
	
	
	public boolean isMyAccountHeadingExists() {
		try {
		return msgMyAccountHeading.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
	
}
