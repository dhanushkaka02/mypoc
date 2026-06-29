package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pageObjects.AdminLogin;
import pageObjects.CheckForPoc;
import pageObjects.PocValidation;
import testbases.BaseClass;

public class TC_007_CheckForPoc extends BaseClass {

    String empName = "charan";

    @Test
    public void validatePOCAccount() {

        AdminLogin lo = new AdminLogin(driver);

        lo.clickAdmin();
        lo.sendAdminName("sudo");
        lo.sendAdminPassword("racingcar123");
        lo.clickAdminLoginButton();

        logger.info("Creating POC Account");

        PocValidation pvc = new PocValidation(driver);

        pvc.setEmpId("2494678");
        pvc.setEmpName(empName);
        pvc.setEmpEmail("charan@gmail.com");
        pvc.setPhoneNo("1234567890");
        pvc.setEmpInitPassword("charan@143");
        pvc.clickCreatePOCAccount();
    }

    @AfterMethod
    public void checkForPOCAccount() {
        CheckForPoc cp = new CheckForPoc(driver);
        cp.clickReloadBtn();

        Assert.assertTrue(cp.checkName(empName),empName);
        logger.info("POC name verified successfully.");
    }
}