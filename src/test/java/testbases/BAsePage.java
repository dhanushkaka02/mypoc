package testbases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BAsePage {

        WebDriver driver;
        public BAsePage(WebDriver driver){
            this.driver = driver;
            PageFactory.initElements(driver, this);

    }

}
