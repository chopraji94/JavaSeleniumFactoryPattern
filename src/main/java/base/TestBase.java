package base;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class TestBase {
    protected WebDriver driver;
    public Properties properties;

    @BeforeClass
    @Parameters("browser")
    public void launch(String browserName) throws IOException {
        DriverFactory.setDriver(browserName);
        driver = DriverFactory.getDriver();

        FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/test.properties");
        properties = new Properties();
        properties.load(fs);

    }

    @BeforeMethod
    public void beforeMethod(Method method){
        long threadId = Thread.currentThread().getId();
        System.out.println("[Thread-" + threadId + "] Starting Method -> " + method.getName());
    }

    @AfterMethod
    public void afterMethod(Method method){
        long threadId = Thread.currentThread().getId();
        System.out.println("[Thread-" + threadId + "] Finished Method -> " + method.getName());
    }

    @AfterClass
    public void close(){
        DriverFactory.quitDriver();
    }
}
