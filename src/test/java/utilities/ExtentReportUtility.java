package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testbases.BaseClass;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportUtility implements ITestListener {
    public ExtentReports extent;
    public ExtentSparkReporter spark;
    public ExtentTest test;
    String repName;

    public void onStart(ITestContext context){
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
       Date dt =  new Date();
       String cdate = sdf.format(dt);
       repName = "Test-Report"+cdate+".html";
       spark = new ExtentSparkReporter(".\\reports\\"+repName);
       spark.config().setDocumentTitle("Test Report");
       spark.config().setReportName("Test Report");
       spark.config().setTheme(Theme.STANDARD);
       extent = new ExtentReports();
       extent.attachReporter(spark);
       extent.setSystemInfo("Windows","2294367");
        List<String> incG = context.getCurrentXmlTest().getIncludedGroups();
        if(!incG.isEmpty()) {
            extent.setSystemInfo("Groups", incG.toString());
        }

        }
    public void onTestSuccess(ITestResult result){
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS,result.getName()+"test executed");
    }
    public void onTestFailure(ITestResult result){
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL,result.getName()+"test not executed");
        test.log(Status.INFO,result.getThrowable().getMessage());
        try{
            String imgPath = new BaseClass().captureScreenshot(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void onTestSkipped(ITestResult result){
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,result.getName()+"test skipped");
        test.log(Status.INFO,result.getThrowable().getMessage());
    }
    public void onFinish(ITestContext context){
        extent.flush();
    }

}

