import java.io.IOException;
import java.io.File;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.MalformedURLException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.example.constant.ConfigConstant.HEADLESS;
import static org.example.constant.ConfigConstant.PAGE_LOAD_TO;
import static org.example.constant.ConfigConstant.SELENIUM_URL;

public abstract class AbstractTest {

    public final Logger LOGGER = LogManager.getLogger(AbstractTest.class);

    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    public WebDriver getDriver() {
        return drivers.get();
    }

    @BeforeMethod
    public void setUp() {
        try {
            ChromeOptions chromeOptions = createChromeOptions();
            WebDriver driver = createRemoteWebDriver(chromeOptions);
            configureDriverTimeouts(driver);
            drivers.set(driver);
        } catch (Exception e) {
            LOGGER.error("Error occurred during setup: {}", e.getMessage());
        }
    }

    private ChromeOptions createChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setEnableDownloads(true);
        if (HEADLESS) {
            chromeOptions.addArguments("--headless");
        }
        return chromeOptions;
    }

    private WebDriver createRemoteWebDriver(ChromeOptions chromeOptions) throws MalformedURLException {
        return new RemoteWebDriver(new URL(SELENIUM_URL), chromeOptions);
    }

    private void configureDriverTimeouts(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TO));
    }

    @AfterMethod
    public void tearDown(ITestResult testResult, Method method) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("src/test/resources/screenshots/" + method.getDeclaringClass() + " " + method.getName() + ".jpg"));
        }
        getDriver().quit();
    }
}
