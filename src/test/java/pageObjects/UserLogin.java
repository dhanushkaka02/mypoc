package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testbases.BAsePage;

import java.time.Duration;

public class UserLogin extends BAsePage {
    WebDriver driver;
    WebDriverWait wait;
    public UserLogin(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @FindBy(xpath="//body/app-root/app-role-selection/div[@class='min-h-screen bg-gradient-to-br from-slate-50 to-slate-100 flex flex-col items-center justify-center px-4 py-12 font-sans']/div[@class='grid grid-cols-1 md:grid-cols-2 gap-8 w-full max-w-3xl']/div[2]/div[1]")
    WebElement userlogin;
    @FindBy(xpath = "//input[@placeholder='e.g. EMP001']")
    WebElement userId;
    @FindBy(xpath ="//input[@placeholder='••••••••']")
    WebElement userPwd;
    @FindBy(xpath = "//button[@class='w-full mt-8 bg-gradient-to-r from-blue-600 to-blue-700 hover:from-blue-700 hover:to-blue-800 active:from-blue-800 active:to-blue-900 text-white font-semibold py-3 rounded-xl transition-all duration-200 shadow-lg hover:shadow-xl hover:-translate-y-0.5 active:shadow-md active:translate-y-0 flex items-center justify-center gap-2']")
    WebElement login;
    @FindBy(xpath="//input[@placeholder='Enter new password']")
    WebElement newpwd;
    @FindBy(xpath = "//input[@placeholder='Confirm new password']")
    WebElement confirmnewpwd;
    @FindBy(xpath = "//button[normalize-space()='Change Password']")
    WebElement clickNewLogin;
    @FindBy(xpath = "//h1[normalize-space()='Change Password']")
    WebElement changePasswordMsg;
    @FindBy(xpath = "//button[contains(text(),'\uD83D\uDEAA Logout')]")
    WebElement userLogout;
    @FindBy(xpath = "//p[@class='text-sm font-medium text-red-900']")
    WebElement failedmsg;
    public void clickUser(){
        userlogin.click();
    }
    public void sendUserName(String userName){
        userId.sendKeys(userName);
    }
    public void sendUserPassword(String pwd){
        userPwd.sendKeys(pwd);
    }
    public void clickUserLoginButton(){
        login.click();
    }
    public void sendNewpwd(String newEpwd){
        newpwd.sendKeys(newEpwd);
    }
    public void sendConfirmNewpwd(String confirmNewpwd){
        confirmnewpwd.sendKeys(confirmNewpwd);
    }
    public void clickNewLoginButton(){
        clickNewLogin.click();
    }
    public String userDisplayMsg() {
        wait.until(ExpectedConditions.visibilityOf(changePasswordMsg));
        return changePasswordMsg.getText();
    }
    public void userLogout(){
        wait.until(ExpectedConditions.visibilityOf(userLogout));
        userLogout.click();
    }
    public String failedDisplay(){
        wait.until(ExpectedConditions.visibilityOf(failedmsg));
        return failedmsg.getText();
    }
}
