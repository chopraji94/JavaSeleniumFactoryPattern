import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserHomePage;

import java.time.Duration;

public class TestLogin2 extends TestBase {
    String userName,password;
    LoginPage loginPage;
    UserHomePage userHomePage;

    @Test(priority = 0)
    public void setup2(){
        System.out.println("Starting login");
        loginPage = new LoginPage(driver);
        userHomePage = new UserHomePage(driver);
        String webUrl = properties.getProperty("launchUrl");
        userName = properties.getProperty("invalidUserName");
        password = properties.getProperty("invalidPassword");
        driver.get(webUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @Test(priority = 1)
    public void login2() throws InterruptedException {
        loginPage.enterUserName(userName);
        loginPage.enterUserPassword(password);
        loginPage.clickSubmitButton();
        Thread.sleep(Duration.ofSeconds(10));
    }

    @Test(priority = 2)
    public void checkIfLoggedIssue2(){
        Assert.assertTrue(loginPage.checkIfErrorToasterDisplayed(),"The User is logged in");
    }
}
