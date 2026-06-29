package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminLogin;
import pageObjects.Logout;
import testbases.BaseClass;
import utilities.DataProviders;


public class TS_01_ADMIN_LOGIN_AND_VALIDATION extends BaseClass {

    @Test(dataProvider ="Admin", dataProviderClass= DataProviders.class)
    public void testLogin(String adminname,String adminpassword,String validation,String expected,String result){

        logger.info(" Admin Login test Start");
        AdminLogin lp = new AdminLogin(driver);

        if(!driver.getCurrentUrl().contains("admin")){
            lp.clickAdmin();
        }
        lp.sendAdminName(adminname);
        lp.sendAdminPassword(adminpassword);
        lp.clickAdminLoginButton();

        String displayMsg = "Admin Control Center";
        Logout lo = new Logout(driver);
        if(displayMsg.equals(lo.headerMsg())){
            lo.clickLogoutButton();
        }else{
            String error =lp.checkError();
            if(error.equals(expected)){
                Assert.assertTrue(true);
            }else{
                Assert.fail();
            }
        }
        logger.info(" Admin Login test End");
    }

}
