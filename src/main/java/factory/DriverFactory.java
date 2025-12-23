package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setDriver(String browser){
        if(driverThreadLocal.get() == null){
            WebDriver driver;

            switch (browser.toLowerCase()){
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser : "+browser);
            }

            driver.manage().window().maximize();
            driverThreadLocal.set(driver);
        }
    }

    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    public static void quitDriver(){
        if(driverThreadLocal.get() != null){
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}
