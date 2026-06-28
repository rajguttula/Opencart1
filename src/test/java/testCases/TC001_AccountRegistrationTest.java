package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration() {
		logger.info("***Strting TC001_AccountRegistrationTest ****");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on Myaccount Link");
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		AccountRegistrationPage register=new AccountRegistrationPage(driver);
		logger.info("Providing customer details");
		register.setFirstName(randomString().toUpperCase());
		register.setLastName(randomString().toUpperCase());
		register.setEmail(randomString().toLowerCase()+"@gmail.com");
		register.setTelPhone(randomNumber());
		String confPWD=randomAlphaNumber();
		register.setPassword(confPWD);
		register.setConfirmPassword(confPWD);
		register.clickPrivacyPolicy();
		register.clickContinue();
		logger.info("Validating expected message");
		String confMsg=register.getMsgConfirmation();
		if(confMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confMsg, "Your Account Has Been Created!");
		
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("****Finished TC001_AccountRegistrationTest ****");
	}
	
	
}
