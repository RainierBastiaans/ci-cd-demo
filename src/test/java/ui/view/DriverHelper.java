package ui.view;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverHelper {
    private static WebDriver driver;

    public static WebDriver getDriver(){
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}