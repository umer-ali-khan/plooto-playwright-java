package base;

import com.microsoft.playwright.*;
import enums.Browsers;
import enums.Environments;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

import static enums.Browsers.CHROME;

public class BaseTests {

    private Browser browser;
    protected LoginPage loginPage;
    protected Page page;
    public static Properties envConfig;
    protected static String ENV = System.getProperty("env", Environments.QA.name());
    protected static String BROWSER = System.getProperty("browser", CHROME.name());

    @BeforeClass
    public void setUp() throws IOException {
        if(System.getProperty("env") != null) {
            ENV = Environments.valueOf(System.getProperty("env").toUpperCase()).name();

            if (System.getProperty("browser") != null) {
                BROWSER = Browsers.valueOf(System.getProperty("browser").toUpperCase()).name();
            }
            switch (BROWSER) {
                case "CHROME": {
                    browser = Playwright
                            .create()
                            .chromium()
                            .launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                }
                case "FIREFOX": {
                    browser = Playwright
                            .create()
                            .firefox()
                            .launch(new BrowserType.LaunchOptions().setHeadless(false));
                    break;
                }
                case "EDGE": {
                    browser = Playwright
                            .create()
                            .chromium()
                            .launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge"));
                    break;
                }
                default:
                    throw new IllegalArgumentException(System.getProperty("browser"));
            }
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource(System.getProperty("env") + ".properties")).getFile());
            String absolutePath = file.getAbsolutePath();
            InputStream configFile = new FileInputStream(absolutePath);
            envConfig = new Properties();
            envConfig.load(configFile);
            page = browser.newPage();
            page.navigate(envConfig.getProperty("baseUrl"));
            loginPage = new LoginPage(page);
        }
    }

    @AfterClass
    public void tearDown(){
        browser.close();
    }
}