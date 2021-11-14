package base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;

public class BaseTests {

    private Browser browser;
    protected LoginPage loginPage;
    protected Page page;

    @BeforeClass
    public void setUp(){

        //Open a browser (supports Chromium (Chrome, Edge), Firefox, and Webkit (Safari))
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));

        //A single browser tab
        page = browser.newPage();
        page.navigate("https://app.plooto.com/qa-test-j1K3eVzQ/login.html");
        loginPage = new LoginPage(page);
    }

    @AfterClass
    public void tearDown(){
        browser.close();
    }
}