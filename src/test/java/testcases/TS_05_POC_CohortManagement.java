package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.PocDashboardPage;
import pageObjects.UserLoginPage;
import testbases.BaseClass;

/**
 * TS-05 : POC Cohort Management
 *
 * TC-POC-013 : Create cohort with valid BatchCode and TrackName → success
 * TC-POC-014 : Create cohort with duplicate BatchCode → error shown
 * TC-POC-015 : Click cohort row → cohort details and Onboard Intern option visible
 */
public class TS_05_POC_CohortManagement extends BaseClass {

    private static final String BATCH_CODE  = "BATCH222";
    private static final String TRACK_NAME  = "Fullstack";

    /**
     * Ensure POC is logged in before running cohort tests.
     * If already on dashboard (prior suite ran TC-POC-011) this is a no-op guard.
     */
    @BeforeClass
    public void loginAsPoc() {
        logger.info("TS-05 BeforeClass: ensuring POC is logged in");

        if (!driver.getCurrentUrl().contains("dashboard")) {
            driver.get("https://sync-inv2.vercel.app/login");
            UserLoginPage loginPage = new UserLoginPage(driver);
            loginPage.enterEmpId("666666");
            loginPage.enterPassword("123456");
            loginPage.clickLogin();
        }
    }

    /**
     * TC-POC-013
     * Precondition : Logged in as POC
     * Steps        : Open Create Cohort form, enter BatchCode BATCH100 and TrackName Fullstack, Submit
     * Expected     : Success message shown; new cohort appears in My Cohorts table
     */
    @Test(priority = 1)
    public void TC_POC_013_CreateCohortSuccess() {
        logger.info("TC-POC-013: Create cohort - Start");

        PocDashboardPage dashboard = new PocDashboardPage(driver);
        dashboard.enterBatchCode(BATCH_CODE);
        dashboard.enterTrackName(TRACK_NAME);
        dashboard.clickCreateCohort();

        Assert.assertTrue(dashboard.isCohortSuccessDisplayed());

        Assert.assertTrue(dashboard.isCohortPresentInTable(BATCH_CODE));

        logger.info("TC-POC-013: Cohort created successfully. Message: " + dashboard.getCohortSuccessMsg());
    }

    /**
     * TC-POC-014
     * Precondition : POC logged in; BATCH100 cohort already exists from TC-POC-013
     * Steps        : Try creating cohort with same BatchCode BATCH100
     * Expected     : UI shows error "Batch code already exists" and creation fails
     */
    @Test(priority = 2, dependsOnMethods = "TC_POC_013_CreateCohortSuccess")
    public void TC_POC_014_DuplicateBatchCode() {
        logger.info("TC-POC-014: Duplicate batch code - Start");

        PocDashboardPage dashboard = new PocDashboardPage(driver);
        dashboard.enterBatchCode(BATCH_CODE);
        dashboard.enterTrackName(TRACK_NAME);
        dashboard.clickCreateCohort();

        if(dashboard.isCohortErrorDisplayed()) {
            Assert.assertTrue(true);
            System.out.println("1");
        }
        else
            Assert.assertTrue(false);

        String errorText = dashboard.getCohortErrorMsg();
        if (errorText.contains("Batch code already exists")) {
            Assert.assertTrue(true);
            System.out.println("2");
        }
        else
            Assert.assertTrue(false);

        logger.info("TC-POC-014: Duplicate batch code error verified. Message: " + errorText);
    }

    /**
     * TC-POC-015
     * Precondition : POC has cohorts (BATCH100 created above)
     * Steps        : Click to view cohort details (Open button on cohort row)
     * Expected     : UI shows cohort details with members list and option to onboard interns
     */
    @Test(priority = 3, dependsOnMethods = "TC_POC_013_CreateCohortSuccess")
    public void TC_POC_015_ViewCohortDetails() {
        logger.info("TC-POC-015: View cohort details - Start");

        PocDashboardPage dashboard = new PocDashboardPage(driver);
        System.out.println("clicking");
        dashboard.clickOpenForCohort(BATCH_CODE);
        System.out.println("clicked");

        if(dashboard.isInternsSectionVisible())
        {
            Assert.assertTrue(true);
            System.out.println("interm section");
        }
        else
            Assert.assertTrue(false);

//        Assert.assertTrue(dashboard.isInternsSectionVisible(),
//            "TC-POC-015 FAIL: Interns section not visible after clicking cohort row");

        if(dashboard.isOnboardInternBtnVisible())
        {
            Assert.assertTrue(true);
        }
        else
            Assert.assertTrue(false);

//        Assert.assertTrue(dashboard.isOnboardInternBtnVisible(),
//            "TC-POC-015 FAIL: 'Onboard Intern' button not visible in cohort details");

        logger.info("TC-POC-015: Cohort details visible with Interns section and Onboard Intern button");
    }
}
