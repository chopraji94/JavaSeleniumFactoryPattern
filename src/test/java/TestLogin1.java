import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserHomePage;

import java.time.Duration;

public class TestLogin1 extends TestBase {

    LoginPage loginPage;
    String userName,password;

    @Test(priority = 0)
    public void setup1(){
        System.out.println("Starting login");
        loginPage = new LoginPage(driver);
        String webUrl = properties.getProperty("launchUrl");
        userName = properties.getProperty("username");
        password = properties.getProperty("password");
        driver.get(webUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @Test(priority = 1)
    public void login1() throws InterruptedException {
        loginPage.enterUserName(userName);
        loginPage.enterUserPassword(password);
        loginPage.clickSubmitButton();
        Thread.sleep(Duration.ofSeconds(10));
    }

    @Test(priority = 2)
    public void checkIfLoggedIn1(){
        UserHomePage userHomePage = new UserHomePage(driver);
        Assert.assertTrue(userHomePage.checkIfLoggedInTitleIsThere(),"The User is not logged in");
    }
}
