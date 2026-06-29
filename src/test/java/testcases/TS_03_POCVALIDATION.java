package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.UserLogin;
import testbases.BaseClass;
import utilities.DataProviders;

public class TS_03_POCVALIDATION extends BaseClass{
    @Test(dataProvider = "poc", dataProviderClass = DataProviders.class)
    public void newLoginTest(String Empid,String initialpwd,String changepwd,String confirmpwd)
    {
        logger.info("New poc Login Test");
        UserLogin ul = new UserLogin(driver);
        ul.clickUser();
        ul.sendUserName(Empid);
        ul.sendUserPassword(initialpwd);
        ul.clickUserLoginButton();
        String message = "Login Failed";
        if(ul.failedDisplay().equals(message)){
            Assert.assertTrue(true);
        }
    }

}
