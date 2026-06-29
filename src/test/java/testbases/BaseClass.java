package testbases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseClass {
    public static WebDriver driver;
    public static Logger logger;

    @BeforeSuite
    public void setUp() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Ths is starting");
        driver = new EdgeDriver();
        driver.get("https://sync-inv2.vercel.app/");
        driver.manage().window().maximize();

    }

//    @AfterSuite
//    public void tearDown() {
//        driver.quit();
//    }

    public String captureScreenshot(String tname) throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        String tfp = System.getProperty("user.dir")+"\\screenshots\\"+timeStamp+".png";
        File targetFile = new File(tfp);
        screenshot.renameTo(targetFile);
        return tfp;
    }
}
