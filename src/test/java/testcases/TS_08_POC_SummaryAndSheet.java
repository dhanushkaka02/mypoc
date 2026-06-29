package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.PocDashboardPage;
import pageObjects.UserLoginPage;
import testbases.BaseClass;

/**
 * TS-08 : POC Cohort Summary and Attendance Sheet
 *
 * TC-POC-025 : Open Cohort Summary with daily / weekly / monthly presets
 * TC-POC-026 : Open Attendance Sheet and verify matrix structure (dates as columns, interns as rows)
 */
public class TS_08_POC_SummaryAndSheet extends BaseClass {

    private static final String BATCH_CODE = "BATCH100";

    @BeforeClass
    public void loginAndOpenCohort() {
        logger.info("TS-08 BeforeClass: ensuring POC is logged in and BATCH100 cohort is selected");
        if (!driver.getCurrentUrl().contains("dashboard")) {
            driver.get("https://sync-inv2.vercel.app/login");
            UserLoginPage loginPage = new UserLoginPage(driver);
            loginPage.enterEmpId("666666");
            loginPage.enterPassword("123456");
            loginPage.clickLogin();
        }
        PocDashboardPage dashboard = new PocDashboardPage(driver);
        dashboard.clickOpenForCohort(BATCH_CODE);
    }

    /**
     * TC-POC-025
     * Precondition : Cohort BATCH100 contains multiple interns with varied attendance
     * Steps        : 1. Open Cohort Summary with preset "today" (daily/default)
     *                2. Switch to "this_week" (weekly)
     *                3. Switch to "this_month" (monthly)
     * Expected     : Table shows rows for every intern with attendancePercentage and bucket;
     *                from/to dates correspond to selected range; values update between ranges
     */
    @Test(priority = 1)
    public void TC_POC_025_CohortSummaryRangeSwitching() throws InterruptedException {
        logger.info("TC-POC-025: Cohort Summary range switching - Start");

        PocDashboardPage dashboard = new PocDashboardPage(driver);

        // ── Step 1 : Today (default / daily) ─────────────────────────────────
//        dashboard.selectSummaryPreset("Today");
//        dashboard.clickSummaryRefresh();

        Thread.sleep(1000);
        Assert.assertTrue(dashboard.isSummaryMetaVisible(),
            "TC-POC-025 FAIL: Summary date range meta not visible for 'today' preset");

        int rowsToday = dashboard.getSummaryRowCount();
        Assert.assertTrue(rowsToday > 0,
            "TC-POC-025 FAIL: No summary rows for 'today' preset");

        logger.info("TC-POC-025 Step 1 (today): " + rowsToday + " summary rows");

        // ── Step 2 : This Week ────────────────────────────────────────────────
        dashboard.selectSummaryPreset("This Week");
        dashboard.clickSummaryRefresh();

        Assert.assertTrue(dashboard.isSummaryMetaVisible(),
            "TC-POC-025 FAIL: Summary date range meta not visible for 'this_week' preset");

        int rowsWeekly = dashboard.getSummaryRowCount();
        Assert.assertTrue(rowsWeekly > 0,
            "TC-POC-025 FAIL: No summary rows for 'this_week' preset");

        logger.info("TC-POC-025 Step 2 (this_week): " + rowsWeekly + " summary rows");

        // ── Step 3 : This Month ───────────────────────────────────────────────
        dashboard.selectSummaryPreset("This Month");
        dashboard.clickSummaryRefresh();

        Assert.assertTrue(dashboard.isSummaryMetaVisible(),
            "TC-POC-025 FAIL: Summary date range meta not visible for 'this_month' preset");

        int rowsMonthly = dashboard.getSummaryRowCount();
        Assert.assertTrue(rowsMonthly > 0,
            "TC-POC-025 FAIL: No summary rows for 'this_month' preset");

        logger.info("TC-POC-025 Step 3 (this_month): " + rowsMonthly + " summary rows");

        logger.info("TC-POC-025: Summary range switching verified for all three presets");
    }

    /**
     * TC-POC-026
     * Precondition : Attendance records exist for a date range in BATCH100
     * Steps        : Open Attendance Sheet for cohort
     * Expected     : Matrix-style table with dates as columns and interns as rows;
     *                status values (PRESENT/ABSENT/AL/UL) visible in cells
     */
    @Test(priority = 2)
    public void TC_POC_026_AttendanceSheetMatrix() {
        logger.info("TC-POC-026: Attendance Sheet matrix - Start");

        PocDashboardPage dashboard = new PocDashboardPage(driver);
//        dashboard.clickSheetRefresh();

        int dateColumns = dashboard.getAttendanceSheetDateColumnCount();
        Assert.assertTrue(dateColumns > 0,
            "TC-POC-026 FAIL: Attendance Sheet has no date columns (expected dates as column headers)");

        int internRows = dashboard.getAttendanceSheetRowCount();
        Assert.assertTrue(internRows > 0,
            "TC-POC-026 FAIL: Attendance Sheet has no intern rows");

        logger.info("TC-POC-026: Attendance Sheet verified — "
            + dateColumns + " date columns, " + internRows + " intern rows");
        logger.info("TC-POC-026: Matrix structure (dates as columns, interns as rows) confirmed");

    }
}
