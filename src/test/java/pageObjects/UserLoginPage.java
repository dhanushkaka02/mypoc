package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testbases.BAsePage;

import java.time.Duration;

/**
 * Page Object for the User Login page (/  route)
 * XPaths derived from: user-login.html
 */
public class UserLoginPage extends BAsePage {

    WebDriver driver;
    WebDriverWait wait;

    public UserLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ── Locators ─────────────────────────────────────────────────────────────

    /** Page title "SyncIn Login" */
    @FindBy(xpath = "//h1[normalize-space()='SyncIn Login']")
    WebElement pageTitle;

    /** Employee ID input — placeholder = "e.g. EMP001" */
    @FindBy(xpath = "//input[@placeholder='e.g. EMP001']")
    WebElement empIdInput;


    @FindBy(xpath = "//input[@placeholder='••••••••']")
    WebElement passwordInput;

    /** Login button */
    @FindBy(xpath = "//button[@class='w-full mt-8 bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 active:from-blue-800 active:to-blue-900 text-white font-semibold py-3 rounded-xl transition-all duration-200 shadow-lg hover:shadow-xl hover:-translate-y-0.5 active:shadow-md active:translate-y-0 flex items-center justify-center gap-2']")
    WebElement loginButton;

    /** Error banner shown on bad credentials */
    @FindBy(xpath = "//p[@class='text-sm font-medium text-red-900']")
    WebElement errorBanner;

    // ── Actions ────────────────
    //
    // ──────────────────────────────────────────────

    public void enterEmpId(String empId) {
        wait.until(ExpectedConditions.visibilityOf(empIdInput)).clear();
        empIdInput.sendKeys(empId);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorBanner));
        return errorBanner.getText().trim();
    }

    public boolean isErrorDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorBanner));
            return errorBanner.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
