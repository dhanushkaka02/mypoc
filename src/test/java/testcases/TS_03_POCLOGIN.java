package testcases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.UserLogin;
import testbases.BaseClass;
import utilities.DataProviders;

import java.time.Duration;

public class TS_03_POCLOGIN extends BaseClass {
    @Test(dataProvider = "poc", dataProviderClass = DataProviders.class)
    public void loginTest(String Empid,String initialpwd,String changepwd,String confirmpwd){
        logger.info(" poc Login test Start");
        UserLogin ul = new UserLogin(driver);
        ul.clickUser();
        ul.sendUserName(Empid);
        ul.sendUserPassword(initialpwd);
        ul.clickUserLoginButton();

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        try{
            if(wait.until(ExpectedConditions.urlContains("change-password"))){
                String usermsg = "Change Password";
                if(ul.userDisplayMsg().equals(usermsg)){
                    ul.sendNewpwd(changepwd);
                    ul.sendConfirmNewpwd(confirmpwd);
                    ul.clickNewLoginButton();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        ul.userLogout();
        ul.clickUser();
        ul.sendUserName(Empid);
        ul.sendUserPassword(changepwd);
        ul.clickUserLoginButton();




    }
}
