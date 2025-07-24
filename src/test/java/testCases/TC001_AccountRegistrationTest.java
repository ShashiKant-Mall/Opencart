package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups={"Regression","Master"})
	void verify_account_registration() {
		
		logger.info("**** Starting TC001_AccountRegistrationTest ****");
		try {
			
		//Object creation of HomePage class
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked on My Account Link");
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		//Object creation of AccountRegistrationPage class
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String pswd=randomAlphaNumeric();
		regpage.setPassword(pswd);
		regpage.setConfirmPassword(pswd);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
	
		logger.info("Validating expected message");
	    String confmsg=regpage.getConfirmationMsg();
	    System.out.println(confmsg);
	    if(confmsg.equals("Your Account Has Been Created!")) {
	    	Assert.assertTrue(true);
	    }
	    else {
	    	logger.error("Test Failed");
			logger.debug("Debug logs..");
	    	Assert.assertTrue(false);
	    }
	    
	}
	catch(Exception e) {
		
		Assert.fail();
	}
		logger.info("**** Finished TC001_AccountRegistrationTest ****");
		
}	
}
