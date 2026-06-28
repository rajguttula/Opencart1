package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAcountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	//since data provider in other class we need to give a below
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups = "DataDriven")
	public void verify_loginDDT(String email,String pwd,String exp) throws InterruptedException {
		logger.info("***Starting TC003_LoginTest***");
		try
		{
		HomePage ph=new HomePage(driver);
		ph.clickMyAccount();
		ph.clickLogin();
		//Login
		LoginPage login=new LoginPage(driver);
		login.setEmail(email);
		login.setPwd(pwd);
		login.clickLogin();
		//MyAccount
		MyAcountPage myAct=new MyAcountPage(driver);
		boolean targetPage=myAct.isMyAccountHeadingExists();
		if (exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				myAct.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				myAct.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("***Finished TC003_LoginTest***");
	}
}
