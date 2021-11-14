package pages;

import com.microsoft.playwright.Page;

import java.util.List;
import java.util.stream.Collectors;

//import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.ATTACHED;
//import static com.microsoft.playwright.Page.WaitForSelectorOptions.State.DETACHED;
import static org.testng.Assert.*;

public class LoginPage {

    private Page page;

    private String locator_email = "#email";
    private String locator_password = "#passphrase";
    private String locator_signIn = "a[href='company_select.html']";
    private String locator_waitFor_plootoDashboard = "text=Go to Plooto Payments Dashboard to";

    public LoginPage(Page page){
        this.page = page;
    }

    public void login(String userName, String password) {
        page.fill(locator_email, userName);
        page.fill(locator_password, password);
        page.click(locator_signIn);
        page.waitForSelector(locator_waitFor_plootoDashboard);
        assertEquals(page.title(), "Select Your Company | Plooto");
    }

}