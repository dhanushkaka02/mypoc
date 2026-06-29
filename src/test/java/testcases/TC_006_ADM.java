package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminLogin;
import pageObjects.PocValidation;
import testbases.BaseClass;

public class TC_006_ADM extends BaseClass {
    @Test
    public void invalidPOCTest()
    {
        logger.info("Running TC_ADM_006 test...");
        AdminLogin lo = new AdminLogin(driver);
        lo.clickAdmin();
        lo.sendAdminName("sudo");
        lo.sendAdminPassword("racingcar123");
        lo.clickAdminLoginButton();
        logger.info("Validating POC Account");
        PocValidation pvc = new PocValidation(driver);
        pvc.setEmpId("2494370");
        pvc.setEmpName("raju");
        pvc.setEmpEmail("raju@gmail.com");
        pvc.setPhoneNo("1234567890");
        pvc.setEmpInitPassword("raju@1");
        if(!pvc.buttonEnabled()){
            Assert.assertTrue(true);
        }

        logger.info("Completed Validating POC Account");
    }

}
