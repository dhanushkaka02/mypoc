package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testbases.BAsePage;

import java.time.Duration;

public class PocValidation extends BAsePage {

    WebDriverWait wait;

    public PocValidation(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//input[@placeholder='e.g., 1234567']")
    WebElement empId;

    @FindBy(xpath = "//input[@placeholder='e.g., Priya Mehta']")
    WebElement empName;

    @FindBy(xpath = "//input[@placeholder='e.g., priya@company.com']")
    WebElement empEmail;

    @FindBy(xpath = "//input[@placeholder='e.g., 9876543210']")
    WebElement phoneNo;

    @FindBy(xpath = "//input[@placeholder='Minimum 8 characters']")
    WebElement empInitPassword;

    @FindBy(xpath = "//button[normalize-space()='Create POC Account']")
    WebElement createPOCAccount;
    @FindBy(xpath = "/html/body/app-root/app-admin-sudo/div/div/div[2]/div[1]/div[2]/div/p")
    WebElement con;

    public void setEmpId(String empId) {
        wait.until(ExpectedConditions.visibilityOf(this.empId));
        this.empId.sendKeys(empId);
    }

    public void setEmpName(String empName) {
        wait.until(ExpectedConditions.visibilityOf(this.empName));
        this.empName.sendKeys(empName);
    }

    public void setEmpEmail(String empEmail) {
        wait.until(ExpectedConditions.visibilityOf(this.empEmail));
        this.empEmail.sendKeys(empEmail);
    }

    public void setPhoneNo(String phoneNo) {
        wait.until(ExpectedConditions.visibilityOf(this.phoneNo));
        this.phoneNo.sendKeys(phoneNo);
    }

    public void setEmpInitPassword(String empInitPassword) {
        wait.until(ExpectedConditions.visibilityOf(this.empInitPassword));
        this.empInitPassword.sendKeys(empInitPassword);
    }

    public void clickCreatePOCAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(createPOCAccount));
        createPOCAccount.click();
    }
    public String checkConflict(){
        wait.until(ExpectedConditions.visibilityOf(con));
        return con.getText();
    }
    public boolean buttonEnabled(){
        if(createPOCAccount.isEnabled()){
            return true;
        }else{
            return false;
        }
    }



}