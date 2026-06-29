package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testbases.BAsePage;
import testbases.BaseClass;

import java.time.Duration;

public class Logout extends BAsePage {
    WebDriver driver;
    WebDriverWait wait;
    public Logout(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @FindBy(xpath="/html/body/app-root/app-admin-sudo/div/div/div[1]/button")
    WebElement logoutButton;

    @FindBy(xpath = "/html/body/app-root/app-admin-sudo/div/div/div[1]/div/h1")
    WebElement header;

    public String headerMsg(){
        try {
            wait.until(ExpectedConditions.visibilityOf(header));
            return header.getText();
        }catch (Exception e){
            return "";
        }

    }
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }
}
