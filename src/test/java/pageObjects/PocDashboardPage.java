package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testbases.BAsePage;

/**
 * Page Object for the POC Dashboard (/dashboard route)
 * XPaths derived from: poc-dashboard.html and layout.html
 */
public class PocDashboardPage extends BAsePage {

    WebDriver driver;
    WebDriverWait wait;

    public PocDashboardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ── LAYOUT / SIDEBAR ────────────────────────────────────────────────────

    /** Sidebar heading "SyncIn" */
    @FindBy(xpath = "//h1[normalize-space()='SyncIn']")
    WebElement sidebarHeading;

    /** "My Cohorts" nav link in sidebar */
    @FindBy(xpath = "//a[contains(text(),'\uD83D\uDCCB Cohorts')]")
    WebElement navCohorts;

    /** "Leave Inbox" nav link in sidebar */
    @FindBy(xpath = "//span[contains(text(),'\uD83D\uDCE8 Leave Inbox')]")
    WebElement navLeaveInbox;

    /** Logout button in sidebar */
    @FindBy(xpath = "//button[contains(text(),'\uD83D\uDEAA Logout')]")
    WebElement logoutBtn;

    // ── CREATE COHORT SECTION ────────────────────────────────────────────────

    /** "Create Cohort" section heading */
    @FindBy(xpath = "//h2[contains(text(),'➕ Create Cohort')]")
    WebElement createCohortHeading;

    /** Batch Code input — placeholder "e.g. B24-JAVA-01" */
    @FindBy(xpath = "//input[@placeholder='e.g. B24-JAVA-01']")
    WebElement batchCodeInput;

    /** Track Name input — placeholder "e.g. Java Full Stack" */
    @FindBy(xpath = "//input[@placeholder='e.g. Java Full Stack']")
    WebElement trackNameInput;

    /** "+ Create" button */
    @FindBy(xpath = "//button[@class='inline-flex items-center gap-2 bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 text-white text-sm font-semibold px-5 py-2.5 rounded-lg transition shadow-md hover:shadow-lg']")
    WebElement createCohortBtn;

    /** Success message for cohort creation (green banner) */
    @FindBy(xpath = "//div[contains(@class,'text-green-700') and contains(@class,'bg-green-50')]")
    WebElement cohortSuccessMsg;

    /** Error message for cohort creation (red banner) */
    @FindBy(xpath = "//div[@class='mb-3 text-sm text-red-600 bg-red-50 border border-red-300 rounded p-2']")
    WebElement cohortErrorMsg;

    // ── MY COHORTS TABLE ─────────────────────────────────────────────────────

    /** "My Cohorts" section heading */
    @FindBy(xpath = "//h2[contains(text(),'\uD83D\uDC65 My Cohorts')]")
    WebElement myCohortHeading;

    /** All rows in the My Cohorts table body */
    @FindBy(xpath = "//h2[normalize-space()='\uD83D\uDC65 My Cohorts']/following-sibling::div/table/tbody/tr")
    List<WebElement> cohortTableRows;

    /** All Batch Code cells in the My Cohorts table */
    @FindBy(xpath = "//h2[normalize-space()='\uD83D\uDC65 My Cohorts']/following-sibling::div/table/tbody/tr/td[1]")
    List<WebElement> cohortBatchCodeCells;

    // ── COHORT ROW OPEN BUTTON ───────────────────────────────────────────────

    /**
     * Returns the "Open" button for a cohort row that contains the given batchCode.
     * Used to select/expand a cohort.
     */
    public WebElement getOpenButtonForCohort(String batchCode) {
        // xpath: find the tr whose first td text equals batchCode, then click its Open button
        return driver.findElement(
            org.openqa.selenium.By.xpath(
                    "//h2[contains(text(),'My Cohorts')]/following-sibling::div/table/tbody/tr[td[1][normalize-space()='"
                            + batchCode
                            + "']]/td[3]//*[normalize-space()='Open']"
            )
        );
    }

    // ── INTERNS TABLE (visible after cohort selected) ────────────────────────

    /** "Interns —" section heading (appears after cohort row click) */
    @FindBy(xpath = "//h2[contains(text(),'\uD83D\uDC65 Interns')]")
    WebElement internsSectionHeading;

    /** "+ Onboard Intern" button */
    @FindBy(xpath = "//button[contains(text(),'➕ Onboard Intern')]")
    WebElement onboardInternBtn;

    /** All intern rows in the interns table */
    @FindBy(xpath = "//h2[contains(text(),'\uD83D\uDC65 Interns')]//following::table[1]//tbody/tr")
    List<WebElement> internTableRows;

    /** All Emp ID cells in the interns table */
    @FindBy(xpath = "//h2[contains(text(),'\uD83D\uDC65 Interns')]//following::table[1]//tbody/tr/td[1]")
    List<WebElement> internEmpIdCells;



    /** All Role cells in the interns table */
    @FindBy(xpath = "//h2[contains(text(),'\uD83D\uDC65 Interns')]//following::table[1]//tbody/tr/td[5]//span")
    List<WebElement> internRoleCells;

    /** Promote error / success message (shared banner in interns section) */
    @FindBy(xpath = "//div[@class='mb-3 text-sm text-red-600 bg-red-50 border border-red-300 rounded p-2']")
    WebElement promoteErrorMsg;

    @FindBy(xpath = "//div[@class='mb-3 text-sm text-red-600 bg-red-50 border border-red-300 rounded p-2']")
    WebElement promoteSuccessMsg;

    /**
     * Returns the "Promote to CR" button for a given empId row.
     */
    public WebElement getPromoteBtnForIntern(String empId) {
        return driver.findElement(
            org.openqa.selenium.By.xpath(
                    "//h2[contains(.,'Interns')]/following::table[1]//tbody/tr[td[1][normalize-space()='"
                            + empId
                            + "']]/td[6]//button[normalize-space()='Promote to CR']"
            )
        );
    }

    /**
     * Returns the "Demote to Intern" button for a given empId row.
     */
    public WebElement getDemoteBtnForIntern(String empId) {
        return driver.findElement(
            org.openqa.selenium.By.xpath(
                "//h2[contains(text(),' Interns')]/following::table[1]//tbody/tr[td[1][normalize-space()='"
                + empId + "']]/td[6]/button[contains(.,'Demote to Intern')]"
            )
        );
    }

    /**
     * Returns the role text for a given empId in the interns table.
     */
    public String getRoleForIntern(String empId) {

        By roleLocator = By.xpath(
                "//tr[td[1][normalize-space()='" + empId + "']]//td[5]//span");

        wait.until(ExpectedConditions.visibilityOfElementLocated(roleLocator));

        return driver.findElement(roleLocator).getText().trim();
    }

    // ── ONBOARD INTERN MODAL ─────────────────────────────────────────────────

    /** "Onboard Intern" modal heading */
    @FindBy(xpath = "//h3[normalize-space()='Onboard Intern']")
    WebElement onboardModalHeading;

    /** Employee ID input in modal — placeholder "EMP001" */
    @FindBy(xpath = "//input[@placeholder='EMP001']")
    WebElement internEmpIdInput;

    /** Full Name input in modal — placeholder "John Doe" */
    @FindBy(xpath = "//input[@placeholder='John Doe']")
    WebElement internNameInput;

    /** Email input in modal — placeholder "john@example.com" */
    @FindBy(xpath = "//input[@placeholder='john@example.com']")
    WebElement internEmailInput;

    /** Mobile No input in modal — placeholder "+91 9876543210" */
    @FindBy(xpath = "//input[@placeholder='+91 9876543210']")
    WebElement internMobileInput;

    /** "Onboard" submit button in modal */
    @FindBy(xpath = "//button[normalize-space()='Onboard']")
    WebElement onboardSubmitBtn;

    /** "Close" button in modal */
    @FindBy(xpath = "//button[normalize-space()='Close']")
    WebElement onboardCloseBtn;

    /** Success message inside modal (green) */
    @FindBy(xpath = "//div[@class='mb-3 text-sm text-green-700 bg-green-50 border border-green-300 rounded p-2']")
    WebElement onboardSuccessMsg;

    /** Temp password shown in modal (blue banner) */
    @FindBy(xpath = "//strong[@class='select-all']")
    WebElement tempPasswordBanner;

    /** Error message inside modal (red) */
    @FindBy(xpath = "//div[@class='mb-3 text-sm text-red-600 bg-red-50 border border-red-300 rounded p-2']")
    WebElement onboardErrorMsg;

    // ── LEAVE INBOX SECTION ──────────────────────────────────────────────────

    /** "Leave Inbox" section heading */
    @FindBy(xpath = "//h2[contains(text(),'\uD83D\uDCE8 Leave Inbox')]")
    WebElement leaveInboxHeading;

    /** All leave request rows in the inbox table */
    @FindBy(xpath = "//h2[contains(normalize-space(),'Leave Inbox')]/following-sibling::table//tbody/tr")
    List<WebElement> leaveInboxRows;

    /** Success message in leave inbox (green) */
    @FindBy(xpath = "//div[@id='leaves']//div[contains(@class,'text-green-700')]")
    WebElement leaveSuccessMsg;

    /** Error message in leave inbox (red) */
    @FindBy(xpath = "//div[@id='leaves']//div[contains(@class,'text-red-600')]")
    WebElement leaveErrorMsg;

    /**
     * Clicks the Approve button on the first pending leave row.
     */
    public void clickApproveOnFirstLeave() {
        WebElement approveBtn = driver.findElement(
            org.openqa.selenium.By.xpath(
                "//h2[contains(normalize-space(),'Leave Inbox')]/following-sibling::table//tbody/tr[1]/td[last()]/button[normalize-space()='Approve']"
            )
        );
        wait.until(ExpectedConditions.elementToBeClickable(approveBtn)).click();
    }

    public void clickOnboardCloseBtn(){
        onboardCloseBtn.click();
    }
    /**
     * Clicks the Reject button on the first pending leave row.
     */

    public void clickRejectOnFirstLeave() {
        WebElement rejectBtn = driver.findElement(
            org.openqa.selenium.By.xpath(
                "//h2[contains(normalize-space(),'Leave Inbox')]/following-sibling::table//tbody/tr[1]/td[last()]/button[normalize-space()='Reject']"
            )
        );
        wait.until(ExpectedConditions.elementToBeClickable(rejectBtn)).click();
    }

    /**
     * Clicks the Mark UL button on the first pending leave row.
     */
    public void clickMarkULOnFirstLeave() {
        WebElement markUlBtn = driver.findElement(
            org.openqa.selenium.By.xpath(
                "//h2[contains(normalize-space(),'Leave Inbox')]/following-sibling::table//tbody/tr[1]/td[last()]/button[normalize-space()='Mark UL']"
            )
        );
        wait.until(ExpectedConditions.elementToBeClickable(markUlBtn)).click();
    }

    // ── COHORT SUMMARY SECTION ───────────────────────────────────────────────

    /** "Cohort Summary" heading */
    @FindBy(xpath = "//h2[normalize-space()='Cohort Summary']")
    WebElement cohortSummaryHeading;

    /** Summary preset dropdown (Today / This Week / This Month) */
    @FindBy(xpath = "//select[@class='border-2 border-gray-200 rounded px-3 py-2 text-sm bg-gray-50 ng-pristine ng-valid ng-touched']")
    WebElement summaryPresetDropdown;

    /** "Refresh" button in Cohort Summary */
    @FindBy(xpath = "//select[@class='border-2 border-gray-200 rounded px-3 py-2 text-sm bg-gray-50 ng-untouched ng-valid ng-dirty']")
    WebElement summaryRefreshBtn;

//h2[contains(text(),'📈 Cohort Summary')]
    /** Date range meta row (shows from → to) */
    @FindBy(xpath = "//div[contains(@class,'bg-blue-50') and contains(@class,'border-blue-200')]")
    WebElement summaryMeta;

    /** All rows in the Cohort Summary table */
    @FindBy(xpath = "//h2[contains(text(),'\uD83D\uDCC8 Cohort Summary')]/following::table[1]//tbody/tr")
    List<WebElement> summaryTableRows;

    /** Attendance % cells in summary table */
    @FindBy(xpath = "//h2[contains(text(),'\uD83D\uDCC8 Cohort Summary')]/following::table[1]//tbody/tr/td[4]")
    List<WebElement> summaryAttendanceCells;

    /** Health/Bucket cells in summary table */
    @FindBy(xpath = "//h2[contains(text(),'\uD83D\uDCC8 Cohort Summary')]/following::table[1]//tbody/tr/td[5]//span")
    List<WebElement> summaryHealthCells;

    /** Error in summary section */
    @FindBy(xpath = "//h2[contains(text(),'\uD83D\uDCC8 Cohort Summary')]/following::div[contains(@class,'text-red-600')][1]")
    WebElement summaryErrorMsg;

    // ── ATTENDANCE SHEET SECTION ─────────────────────────────────────────────

    /** "Attendance Sheet" heading */
    @FindBy(xpath = "//h2[normalize-space()='Attendance Sheet']")
    WebElement attendanceSheetHeading;

    /** "Refresh" link/button in Attendance Sheet */
    @FindBy(xpath = "//h2[normalize-space()='Attendance Sheet']/following::button[normalize-space()='Refresh'][1]")
    WebElement sheetRefreshBtn;

    /** All header date columns in the attendance sheet (excluding Emp ID, Name, Role) */
    @FindBy(xpath = "//h2[normalize-space()='Attendance Sheet']/following::table[1]//thead/tr/th[position()>3]")
    List<WebElement> sheetDateHeaders;

    /** All rows in the attendance sheet body */
    @FindBy(xpath = "//h2[normalize-space()='Attendance Sheet']/following::table[1]//tbody/tr")
    List<WebElement> sheetBodyRows;

    /** Error in attendance sheet section */
    @FindBy(xpath = "//h2[normalize-space()='Attendance Sheet']/following::div[contains(@class,'text-red-600')][1]")
    WebElement sheetErrorMsg;

    // ── ACTIONS ──────────────────────────────────────────────────────────────

    public void enterBatchCode(String batchCode) {
        wait.until(ExpectedConditions.visibilityOf(batchCodeInput)).clear();
        batchCodeInput.sendKeys(batchCode);
    }

    public void enterTrackName(String trackName) {
        wait.until(ExpectedConditions.visibilityOf(trackNameInput)).clear();
        trackNameInput.sendKeys(trackName);
    }

    public void clickCreateCohort() {
        wait.until(ExpectedConditions.elementToBeClickable(createCohortBtn)).click();
    }

    public String getCohortSuccessMsg() {
        wait.until(ExpectedConditions.visibilityOf(cohortSuccessMsg));
        return cohortSuccessMsg.getText().trim();
    }

    public String getCohortErrorMsg() {
        wait.until(ExpectedConditions.visibilityOf(cohortErrorMsg));
        return cohortErrorMsg.getText().trim();
    }

    public boolean isCohortSuccessDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cohortSuccessMsg));
            return cohortSuccessMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCohortErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cohortErrorMsg));
            return cohortErrorMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCohortPresentInTable(String batchCode) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(cohortBatchCodeCells));
            for (WebElement cell : cohortBatchCodeCells) {
                if (cell.getText().trim().equalsIgnoreCase(batchCode)) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void clickOpenForCohort(String batchCode) {
        if(batchCode.equals("BATCH100")){
            By openB = By.xpath("//td[normalize-space()='" + batchCode + "']/parent::tr//*[normalize-space()='Open']");
            wait.until(ExpectedConditions.elementToBeClickable(openB)).click();
        }
        else {
            WebElement openBtn = getOpenButtonForCohort(batchCode);
            wait.until(ExpectedConditions.elementToBeClickable(openBtn)).click();
        }
    }

    public boolean isInternsSectionVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(internsSectionHeading));
            return internsSectionHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOnboardInternBtnVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(onboardInternBtn));
            return onboardInternBtn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnboardInternBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(onboardInternBtn)).click();
    }

    public void enterInternEmpId(String empId) {
        wait.until(ExpectedConditions.visibilityOf(internEmpIdInput)).clear();
        internEmpIdInput.sendKeys(empId);
    }

    public void enterInternName(String name) {
        wait.until(ExpectedConditions.visibilityOf(internNameInput)).clear();
        internNameInput.sendKeys(name);
    }

    public void enterInternEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(internEmailInput)).clear();
        internEmailInput.sendKeys(email);
    }

    public void enterInternMobile(String mobile) {
        wait.until(ExpectedConditions.visibilityOf(internMobileInput)).clear();
        internMobileInput.sendKeys(mobile);
    }

    public void clickOnboardSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(onboardSubmitBtn)).click();
    }

    public String getOnboardSuccessMsg() {
        wait.until(ExpectedConditions.visibilityOf(onboardSuccessMsg));
        return onboardSuccessMsg.getText().trim();
    }

    public String getTempPassword() {
        wait.until(ExpectedConditions.visibilityOf(tempPasswordBanner));
        return tempPasswordBanner.getText().trim();
    }

    public boolean isTempPasswordVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(tempPasswordBanner));
            return tempPasswordBanner.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getOnboardErrorMsg() {
        wait.until(ExpectedConditions.visibilityOf(onboardErrorMsg));
        return onboardErrorMsg.getText().trim();
    }

    public boolean isOnboardErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(onboardErrorMsg));
            return onboardErrorMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isInternPresentInTable(String empId) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(internEmpIdCells));
            for (WebElement cell : internEmpIdCells) {
                if (cell.getText().trim().equals(empId)) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void clickPromoteForIntern(String empId){

        By promoteBtn = By.xpath(
                "//tr[td[1][normalize-space()='" + empId + "']]//button[contains(.,'Promote')]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(promoteBtn)).click();
    }

    public void clickDemoteForIntern(String empId) {
        WebElement btn = getDemoteBtnForIntern(empId);
        wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
    }

    public String getPromoteErrorMsg() {
        wait.until(ExpectedConditions.visibilityOf(promoteErrorMsg));
        return promoteErrorMsg.getText().trim();
    }

    public boolean isPromoteErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(promoteErrorMsg));
            return promoteErrorMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLeaveSuccessMsg() {
        wait.until(ExpectedConditions.visibilityOf(leaveSuccessMsg));
        return leaveSuccessMsg.getText().trim();
    }

    public boolean isLeaveSuccessDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(leaveSuccessMsg));
            return leaveSuccessMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getLeaveInboxRowCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(leaveInboxRows));
            return leaveInboxRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void selectSummaryPreset(String preset) {
        wait.until(ExpectedConditions.visibilityOf(summaryPresetDropdown));
        new org.openqa.selenium.support.ui.Select(summaryPresetDropdown).selectByValue(preset);
    }

    public void clickSummaryRefresh() {
        wait.until(ExpectedConditions.elementToBeClickable(summaryRefreshBtn)).click();
    }

    public int getSummaryRowCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(summaryTableRows));
            return summaryTableRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isSummaryMetaVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(summaryMeta));
            return summaryMeta.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getAttendanceSheetDateColumnCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(sheetDateHeaders));
            return sheetDateHeaders.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public int getAttendanceSheetRowCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(sheetBodyRows));
            return sheetBodyRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickSheetRefresh() {
        wait.until(ExpectedConditions.elementToBeClickable(sheetRefreshBtn)).click();
    }

    public boolean isMyCohortHeadingDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(myCohortHeading));
            return myCohortHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCreateCohortHeadingDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(createCohortHeading));
            return createCohortHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLeaveInboxHeadingDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(leaveInboxHeading));
            return leaveInboxHeading.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
    }
}
