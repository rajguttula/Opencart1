package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//input[@name='firstname']") WebElement txtFirstName;
	@FindBy(xpath="//input[@name='lastname']") WebElement txtLastName;
	@FindBy(xpath="//input[@name='email']") WebElement txtEmail;
	@FindBy(xpath="//input[@name='telephone']") WebElement txtTelephone;
	@FindBy(xpath="//input[@name='password']") WebElement txtpassword;
	@FindBy(xpath="//input[@name='confirm']") WebElement txtConfirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement chkAgree;
	@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']") WebElement msgConfirmation;
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setTelPhone(String telPhone) {
		txtTelephone.sendKeys(telPhone);
	}
	public void setPassword(String password) {
		txtpassword.sendKeys(password);
	}
	public void setConfirmPassword(String confirmPassword) {
		txtConfirmPassword.sendKeys(confirmPassword);
	}
	public void clickPrivacyPolicy() {
		chkAgree.click();
	}
	public void clickContinue() {
		//Sol:1
		btnContinue.click();
		
		//Sol:2
		//btnContinue.submit();
		
		//Sol:3
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		//Sol:4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", btnContinue);
		
		//Sol:5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//Sol:6
		//WebDriverWait mywait=new WebDriverWait(driver, Duration.ofSeconds(10));
	    //mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		
	}
	
	
	public String getMsgConfirmation() {
		try {
			return msgConfirmation.getText();
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
}
