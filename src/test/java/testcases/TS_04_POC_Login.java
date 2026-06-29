package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.PocDashboardPage;
import pageObjects.UserLoginPage;
import testbases.BaseClass;

/**
 * TS-04 : POC Login & Dashboard Landing
 *
 * TC-POC-011 : Valid POC login → lands on POC Dashboard
 * TC-POC-012 : Invalid POC credentials → inline error shown
 */
public class TS_04_POC_Login extends BaseClass {

    /**
     * TC-POC-011
     * Precondition : POC account exists (firstLogin already handled – password changed)
     * Steps        : Go to login page, login as POC with valid credentials
     * Expected     : POC lands on POC Dashboard with My Cohorts section,
     *                Create Cohort button and Leave Inbox link visible.
     */
    @Test(priority = 1)
    public void TC_POC_011_ValidPocLogin() {
        logger.info("TC-POC-011: Valid POC login - Start");

        driver.get("https://sync-inv2.vercel.app/login");
        UserLoginPage loginPage = new UserLoginPage(driver);
        loginPage.enterEmpId("666666");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();

        PocDashboardPage dashboard = new PocDashboardPage(driver);

        if(dashboard.isCreateCohortHeadingDisplayed()){
            Assert.assertTrue(true);
//            System.out.println("hello");
        }
        else
            Assert.assertTrue(false);
        if(dashboard.isMyCohortHeadingDisplayed()) {
            Assert.assertTrue(true);
//            System.out.println("hello");
        }
        else
            Assert.assertTrue(false);
        if(dashboard.isLeaveInboxHeadingDisplayed()) {
            Assert.assertTrue(true);
//            System.out.println("hello");
        }
        else
            Assert.assertTrue(false);

        dashboard.clickLogout();
        System.out.println("logout clicked");
        logger.info("TC-POC-011: POC Dashboard verified - My Cohorts, Create Cohort and Leave Inbox all visible");

    }

    /**
     * TC-POC-012
     * Precondition : Invalid POC credentials
     * Steps        : Login with wrong password
     * Expected     : Login rejected; inline error banner shown
     */
    @Test(priority = 2)
    public void TC_POC_012_InvalidPocLogin() {
        logger.info("TC-POC-012: Invalid POC login - Start");

        // Navigate back to login page
        driver.get("https://sync-inv2.vercel.app/login");

        UserLoginPage loginPage = new UserLoginPage(driver);
        loginPage.enterEmpId("POC001");
        loginPage.enterPassword("wrongpassword");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Login Failed");

        logger.info("TC-POC-012: Inline error banner verified for invalid credentials. Message: "
                + loginPage.getErrorMessage());
    }
}
