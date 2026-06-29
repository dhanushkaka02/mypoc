package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testbases.BAsePage;

import java.time.Duration;
import java.util.List;

public class CheckForPoc extends BAsePage {

    WebDriver driver;
    WebDriverWait wait;

    public CheckForPoc(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//button[@class='inline-flex items-center gap-2 px-3 py-2 bg-blue-50 text-blue-700 hover:bg-blue-100 rounded-lg font-medium transition-all text-sm hover:shadow-md']")
    WebElement reloadBtn;

    @FindBy(xpath = "//td[@class='px-4 py-3 font-medium text-gray-900']")
    List<WebElement> userList;

    public void clickReloadBtn() {
        reloadBtn.click();
    }

    public boolean checkName(String expectedName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(userList));

        for (WebElement user : userList) {

            String actualName = user.getText().trim();

            if (actualName.equalsIgnoreCase(expectedName)) {
                return true;
            }
        }
        return false;
    }
}