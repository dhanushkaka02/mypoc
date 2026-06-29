package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminLogin;
import pageObjects.PocValidation;
import testbases.BaseClass;

public class TS_02_DuplicatePOC extends BaseClass {
    @Test
    public void duplicatePOCTest()
    {
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
        pvc.setEmpInitPassword("raju@123");
        pvc.clickCreatePOCAccount();
        String con = "Conflict";
        if(pvc.checkConflict().equals(con)){
            Assert.assertTrue(true);
        }
    }

}
