package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static org.testng.Assert.assertEquals;

public class CompanySelectionPage extends BasePage{
//    private String locator_companyPlootoInc = "//span[@class='company-name d-block' and text()='Plooto Inc']";
//    private String locator_waitFor_plootoSetUpPayments = "a[href='#user/dashboard/setupPayments']";

    public CompanySelectionPage(Page page){
        super(page);
    }

    public void selectPlooto() {
        page.click(locatorRepository.getProperty("locator_companyPlootoInc"));
        page.waitForSelector(locatorRepository.getProperty("locator_waitFor_plootoSetUpPayments"));
        assertEquals(page.title(), "Dashboard | Plooto");
    }

}