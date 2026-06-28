package testCases;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAcountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass  {
		
	@Test(groups = {"Sanity","Master"})
	public void verify_login() {
		logger.info("***Start TC2_LoginTest***");
		//Home
		try
		{
		HomePage ph=new HomePage(driver);
		ph.clickMyAccount();
		ph.clickLogin();
		//Login
		LoginPage login=new LoginPage(driver);
		login.setEmail(p.getProperty("email"));
		login.setPwd(p.getProperty("password"));
		login.clickLogin();
		//MyAccount
		MyAcountPage myAct=new MyAcountPage(driver);
		boolean targetPage=myAct.isMyAccountHeadingExists();
		//Assert.assertEquals(targetPage,true,"Login Failed");
		Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***Finished TC2_LoginTest***");
	}

}
