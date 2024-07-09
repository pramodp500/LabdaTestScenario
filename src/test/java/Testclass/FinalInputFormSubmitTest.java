package Testclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Platform;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FinalInputFormSubmitTest {
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
    public void testInputFormSubmit() throws InterruptedException {
        getDriver().findElement(By.linkText("Input Form Submit")).click();
        WebElement submit = getDriver().findElement(By.xpath("//button[contains(text(),'Submit')]"));
        submit.click();
        WebElement Name = getDriver().findElement(By.id("name"));

        String pleasefilloutthisform = Name.getAttribute("required");
        Assert.assertTrue(true, pleasefilloutthisform);
        String errormsg = "Please fill out this field.";
        Assert.assertTrue(true, errormsg);
        Thread.sleep(5000);
        WebElement country = getDriver().findElement(By.xpath("//*[@id=\'seleniumform\']/div[3]/div[1]/select"));
        country.click();
        getDriver().findElement(By.cssSelector("#seleniumform > div:nth-child(3) > div.form-group.w-6\\/12.smtablet\\:w-full.pr-20.smtablet\\:pr-0 > select > option:nth-child(238)")).click();

        WebElement name = getDriver().findElement(By.xpath("//*[@id=\'name\']"));
        name.sendKeys("Test Lambda");
        Thread.sleep(1000);
        WebElement Email = getDriver().findElement(By.cssSelector("#inputEmail4"));
        Email.sendKeys("TestData@gmail.com");
        Thread.sleep(1000);
        WebElement password = getDriver().findElement(By.id("inputPassword4"));
        password.sendKeys("Test_Lamda#@123");
        Thread.sleep(1000);
        WebElement Company = getDriver().findElement(By.xpath("//*[@id=\'company\']"));
        Company.sendKeys("Test_Lamda pvt ltd.");
        Thread.sleep(1000);
        WebElement Website  = getDriver().findElement(By.cssSelector("#websitename"));
        Website.sendKeys("www.Test_Lamda.com");
        Thread.sleep(1000);
        WebElement City = getDriver().findElement(By.id("inputCity"));
        City.sendKeys("Earth");
        Thread.sleep(1000);
        WebElement Address1 = getDriver().findElement(By.xpath("//*[@id=\'inputAddress1\']"));
        Address1.sendKeys("Earth_Air");
        Thread.sleep(1000);
        WebElement Address2 = getDriver().findElement(By.cssSelector("#inputAddress2"));
        Address2.sendKeys("Earth Water");
        Thread.sleep(1000);
        WebElement state = getDriver().findElement(By.id("inputState"));
        state.sendKeys("Test_Lamda State");
        Thread.sleep(1000);
        WebElement Zipcode =  getDriver().findElement(By.xpath("//*[@id=\'inputZip\']"));
        Zipcode.sendKeys("111111");
        Thread.sleep(1000);
        submit.click();
        WebElement successMessage = getDriver().findElement(By.xpath("//p[contains(text(),'Thanks for contacting us, we will get back to you shortly.')]"));
        Assert.assertTrue(successMessage.isDisplayed(), "Success message not displayed");
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

