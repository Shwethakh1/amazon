package generic;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest implements IAutoConst {
	public WebDriver driver;
	public WebDriverWait wait;
	public static ExtentSparkReporter spark;
	public static ExtentReports report;
	public ExtentTest test;
	
	@BeforeSuite
	public void setReport()
	{
		//report generation
		spark=new  ExtentSparkReporter("./result.html");
		report=new ExtentReports();
		report.attachReporter(spark);

	}
	
	@AfterSuite
	public void publishReport()
	{
		report.flush();
		
	}

	@Parameters({"env"})
	@BeforeMethod
	public void preCondition(Method testMethod, @Optional(ENV)String env) {
		
		String browser=Utility.getProperty(env,"BROWSER");
		test = report.createTest(testMethod.getName());
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			test.info( "Open Chrome in Local Sys");
		}
		else
		{
			driver=new EdgeDriver();
			test.info("Open Edge in Local Sys");
		}
	
	driver.manage().window().maximize();
	test.info("maximize the browser");
	
	String appURL=Utility.getProperty(env,"APPURL");
	driver.get(appURL);
	test.info("enter the app URL:"+appURL);
	
	
	int ITO=Integer.parseInt(Utility.getProperty(env,"ITO"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ITO));
	test.info("Set ITO:"+ITO);
	
	
	int ETO=Integer.parseInt(Utility.getProperty(env,"ETO"));
	wait=new WebDriverWait(driver, Duration.ofSeconds(ETO));
	test.info("Set ETO:"+ETO);

	}

	@AfterMethod
	public void postCondition(ITestResult result) throws IOException {
		String name = result.getName();
		int status = result.getStatus(); //1-PASS 2-FAIL 3-SKIP
         if (status == 2) {

			// Taking screenshot in failed scenario
			SimpleDateFormat date = new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
			String timestamp = date.format(new Date());
			String fileName = name + timestamp;

			TakesScreenshot s = (TakesScreenshot) driver;
			File srcImg = s.getScreenshotAs(OutputType.FILE);
			String path="./image/" + fileName + ".png";
			File destImg = new File(path);
			
				FileUtils.copyFile(srcImg, destImg);
				test.fail("Test:"+name+" is Failed, hence screenshot has been taken:"+path);
				test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	}
     else {
    	 test.pass("Test:"+name+" is PASSED, hence NO screenshot has been taken:");
		}

		driver.quit();
		test.info("Close the browser");
	}

}
