package Testclass;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SimpleFormDemoTest1 {
    private WebDriver driver;
    @BeforeMethod
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("safari");
        capabilities.setPlatform(Platform.CATALINA);

        // Enable network logs, video recording, screenshots, and console logs
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("network", true);
        ltOptions.put("video", true);
        ltOptions.put("visual", true);
        ltOptions.put("console", true);
        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL("https://pramod.pati3155:BQFwguJXb1wpgB6l3o2sLpJnloYG2aIyzeRV8TAAJj5byMtsJw@hub.lambdatest.com/wd/hub"), capabilities);
        driver.get("https://www.lambdatest.com/selenium-playground");
    }

    @Test
    public void testSimpleFormDemo() {
        driver.findElement(By.linkText("Simple Form Demo")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("simple-form-demo"));

        String message = "Welcome to LambdaTest";
        WebElement messageInput = driver.findElement(By.id("user-message"));
        messageInput.sendKeys(message);

        driver.findElement(By.id("showInput")).click();
        WebElement displayedMessage = driver.findElement(By.id("message"));
        Assert.assertEquals(displayedMessage.getText(), message);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

