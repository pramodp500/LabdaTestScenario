package Testclass;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FinalSimpleFormDemoa {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Parameters({"browser", "version", "platform"})
    @BeforeMethod
    public void setUp(String browser, String version, String platform) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setVersion(version);
        capabilities.setPlatform(Platform.fromString(platform));

        // Enable network logs, video recording, screenshots, and console logs
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("network", true);
        ltOptions.put("video", true);
        ltOptions.put("visual", true);
        ltOptions.put("console", true);
        capabilities.setCapability("LT:Options", ltOptions);

        driver.set(new RemoteWebDriver(new URL("https://pramod.pati3155:BQFwguJXb1wpgB6l3o2sLpJnloYG2aIyzeRV8TAAJj5byMtsJw@hub.lambdatest.com/wd/hub"), capabilities));
        getDriver().get("https://www.lambdatest.com/selenium-playground");
    }

    @Test
    public void testSimpleFormDemo() {
        getDriver().findElement(By.linkText("Simple Form Demo")).click();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("simple-form-demo"));

        String message = "Welcome to LambdaTest";
        WebElement messageInput = getDriver().findElement(By.id("user-message"));
        messageInput.sendKeys(message);

        getDriver().findElement(By.id("showInput")).click();
        WebElement displayedMessage = getDriver().findElement(By.id("message"));
        Assert.assertEquals(displayedMessage.getText(), message);
    }

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        driver.remove();
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}

