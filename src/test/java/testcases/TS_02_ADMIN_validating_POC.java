package testcases;

import org.testng.annotations.Test;
import pageObjects.AdminLogin;
import pageObjects.PocValidation;
import testbases.BaseClass;
import utilities.DataProviders;

public class TS_02_ADMIN_validating_POC extends BaseClass{

    @Test
    public void validatePOCAccount(){
        AdminLogin lo = new AdminLogin(driver);
        lo.clickAdmin();
        lo.sendAdminName("sudo");
        lo.sendAdminPassword("racingcar123");
        lo.clickAdminLoginButton();
        logger.info("Validating POC Account");
        PocValidation pvc = new PocValidation(driver);
        pvc.setEmpId("2494786");
        pvc.setEmpName("rahul");
        pvc.setEmpEmail("rahul@gmail.com");
        pvc.setPhoneNo("1234567890");
        pvc.setEmpInitPassword("rahul@123");
        pvc.clickCreatePOCAccount();
    }
}
