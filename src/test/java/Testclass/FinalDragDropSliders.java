package Testclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FinalDragDropSliders {
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
    public void testDragDropSliders() {
        getDriver().findElement(By.linkText("Drag & Drop Sliders")).click();
        WebElement slider = getDriver().findElement(By.xpath("//input[@value='15']"));
        Actions move = new Actions(getDriver());
        move.dragAndDropBy(slider, 215, 0).perform();  // Adjust offset as needed
        WebElement value = getDriver().findElement(By.id("rangeSuccess"));
        Assert.assertEquals(value.getText(), "95");
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

