package pages;

import com.microsoft.playwright.Page;

import java.util.List;
import java.util.stream.Collectors;

//import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.ATTACHED;
//import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.DETACHED;
import static org.testng.Assert.*;

public class LoginPage extends BasePage{

//    private String locator_email = "#email";
//    private String locator_password = "#passphrase";
//    private String locator_signIn = "a[href='company_select.html']";
//    private String locator_waitFor_plootoDashboard = "text=Go to Plooto Payments Dashboard to";

    public LoginPage(Page page){
       super(page);
    }

    public void login(String userName, String password) {
        page.fill(locatorRepository.getProperty("locator_email"), userName);
        page.fill(locatorRepository.getProperty("locator_password"), password);
        page.click(locatorRepository.getProperty("locator_signIn"));
        page.waitForSelector(locatorRepository.getProperty("locator_waitFor_plootoDashboard"));
        assertEquals(page.title(), "Select Your Company | Plooto");
    }

}