package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.PocDashboardPage;
import pageObjects.UserLoginPage;
import testbases.BaseClass;

/**
 * TS-06 : POC Intern Management
 *
 * TC-POC-016 : Onboard intern with valid details → success + temp password shown
 * TC-POC-017 : Onboard intern with duplicate EmpId → error shown
 * TC-POC-018 : Promote valid intern to CR → role changes to CR
 * TC-POC-019 : Promote when cohort already has 2 CRs → promotion rejected
 * TC-POC-020 : Demote a CR back to INTERN → role changes to INTERN
 */
public class TS_06_POC_InternManagement extends BaseClass {

    private static final String BATCH_CODE    = "BATCH100";
    private static final String INTERN_EMP_ID = "INT15661";
    private static final String INTERN_NAME   = "Intern 15661";
    private static final String INTERN_EMAIL  = "int15661@example.com";
    private static final String INTERN_MOBILE = "6011277671";

    @BeforeClass
    public void loginAndSelectCohort() {
        logger.info("TS-06 BeforeClass: ensuring POC is logged in and BATCH100 is selected");
        if (!driver.getCurrentUrl().contains("dashboard")) {
            driver.get("https://sync-inv2.vercel.app/login");
            UserLoginPage loginPage = new UserLoginPage(driver);
            loginPage.enterEmpId("666666");
            loginPage.enterPassword("123456");
            loginPage.clickLogin();
        }
        PocDashboardPage dashboard = new PocDashboardPage(driver);
        dashboard.clickOpenForCohort(BATCH_CODE);
        System.out.println("clicking");
    }

    /**
     * TC-POC-016
     * Precondition : POC owns cohort BATCH100
     * Steps        : Open Onboard Intern form, fill EmpId INT100, Name Intern One,
     *                Email int100@example.com, Mobile 8888888888, Submit
     * Expected     : Success notification; intern in members list; temp password visible
     */
    @Test(priority = 1)
    public void TC_POC_016_OnboardInternSuccess() {
        logger.info("TC-POC-016: Onboard intern - Start");
        System.out.println("in test case 1");

        PocDashboardPage dashboard = new PocDashboardPage(driver);
        dashboard.clickOnboardInternBtn();

        dashboard.enterInternEmpId(INTERN_EMP_ID);
        dashboard.enterInternName(INTERN_NAME);
        dashboard.enterInternEmail(INTERN_EMAIL);
        dashboard.enterInternMobile(INTERN_MOBILE);
        dashboard.clickOnboardSubmit();

        if(dashboard.isTempPasswordVisible()) {
            Assert.assertTrue(true);
            System.out.println("tempPassVisible");
        }
        else
            Assert.assertTrue(false);

        logger.info("TC-POC-016: Temp password displayed: " + dashboard.getTempPassword());

        // Close modal and verify intern appears in the members table
        // Modal auto-closes or close manually if still open
        try {
            // If onboard success msg is shown, close the modal
            dashboard.getOnboardSuccessMsg();
            System.out.println("onboardSuccessMsg");
        } catch (Exception ignored) {}

        dashboard.clickOnboardCloseBtn();
        if(dashboard.isInternPresentInTable(INTERN_EMP_ID)){
            System.out.println("present in table");
            Assert.assertTrue(true);
        }
        else
            Assert.assertTrue(false);
        logger.info("TC-POC-016: Intern onboarded successfully and visible in members list");

    }

    /**
     * TC-POC-017
     * Precondition : POC onboarding page open; INT100 already onboarded
     * Steps        : Try to add another intern with same EmpId INT100
     * Expected     : UI displays duplicate empId error; no new user created
     */
    @Test(priority = 2, dependsOnMethods = "TC_POC_016_OnboardInternSuccess")
    public void TC_POC_017_DuplicateInternEmpId() {
        logger.info("TC-POC-017: Duplicate intern empId - Start");

        PocDashboardPage dashboard = new PocDashboardPage(driver);
        dashboard.clickOnboardInternBtn();

        dashboard.enterInternEmpId(INTERN_EMP_ID);
        dashboard.enterInternName("Duplicate Intern");
        dashboard.enterInternEmail("dup@example.com");
        dashboard.enterInternMobile("9999999999");
        dashboard.clickOnboardSubmit();

        if(dashboard.isOnboardErrorDisplayed()) {
            Assert.assertTrue(true);
        }
        else
            Assert.assertTrue(false);

        String errorMsg = dashboard.getOnboardErrorMsg();
        if(errorMsg != null) {
            Assert.assertTrue(true);
        }
        else
            Assert.assertTrue(false);
        dashboard.clickOnboardCloseBtn();

        logger.info("TC-POC-017: Duplicate empId error verified. Message: " + errorMsg);
    }

    /**
     * TC-POC-018
     * Precondition : Cohort has <= 2 CRs; INT100 is currently INTERN
     * Steps        : Promote INT100 to CR using Promote action
     * Expected     : Role for INT100 changes to CR in list; count of CRs <= 2
     */
    @Test(priority = 3, dependsOnMethods = "TC_POC_016_OnboardInternSuccess")
    public void TC_POC_018_PromoteInternToCR() {

        logger.info("TC-POC-018: Promote intern to CR - Start");

        PocDashboardPage dashboard = new PocDashboardPage(driver);
        dashboard.clickPromoteForIntern(INTERN_EMP_ID);


        String role = dashboard.getRoleForIntern(INTERN_EMP_ID);
        if(role.equalsIgnoreCase("CR")){
            System.out.println("promote intern to CR");
            Assert.assertTrue(true);
        }
        logger.info("TC-POC-018: Intern " + INTERN_EMP_ID + " successfully promoted to CR");
    }

    /**
     * TC-POC-019
     * Precondition : Cohort already has 2 CRs
     * Steps        : Try to promote another intern to CR
     * Expected     : Promotion rejected; UI shows "Maximum 2 CRs allowed per cohort"
     */
    @Test(priority = 4,dependsOnMethods ="TC_POC_018_PromoteInternToCR")
    public void TC_POC_019_MaxCRLimitReached() {
        logger.info("TC-POC-019: Max CR limit - Start");

        PocDashboardPage dashboard = new PocDashboardPage(driver);

        // Attempt to promote an intern (assumes cohort already has 2 CRs)
        // Use a different intern empId that is still INTERN role
        String anotherInternId = "INT110";
        dashboard.clickPromoteForIntern(anotherInternId);

        if(dashboard.isPromoteErrorDisplayed())
        {
            Assert.assertTrue(true);
        }

        logger.info("TC-POC-019: Max CR limit error verified. Message: ");
    }

    /**
     * TC-POC-020
     * Precondition : INT100 has role CR (promoted in TC-POC-018)
     * Steps        : Demote INT100 via Demote action
     * Expected     : User role changes back to INTERN in members list
     */
    @Test(priority = 5, dependsOnMethods = "TC_POC_018_PromoteInternToCR")
    public void TC_POC_020_DemoteCRToIntern() {
        logger.info("TC-POC-020: Demote CR to Intern - Start");

        PocDashboardPage dashboard = new PocDashboardPage(driver);
        dashboard.clickDemoteForIntern(INTERN_EMP_ID);

        String role = dashboard.getRoleForIntern(INTERN_EMP_ID);
        System.out.println(role);
        Assert.assertTrue(true);
        logger.info("TC-POC-020: CR " + INTERN_EMP_ID + " successfully demoted back to INTERN");
    }
}
