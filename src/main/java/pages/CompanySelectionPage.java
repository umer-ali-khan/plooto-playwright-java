package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static org.testng.Assert.assertEquals;

public class CompanySelectionPage {

    private Page page;

    private String locator_companyPlootoInc = "//span[@class='company-name d-block' and text()='Plooto Inc']";
    private String locator_waitFor_plootoSetUpPayments = "a[href='#user/dashboard/setupPayments']";

    public CompanySelectionPage(Page page){
        this.page = page;
    }

    public void selectPlooto() {
        page.click(locator_companyPlootoInc);
        page.waitForSelector(locator_waitFor_plootoSetUpPayments);
        assertEquals(page.title(), "Dashboard | Plooto");
    }

}