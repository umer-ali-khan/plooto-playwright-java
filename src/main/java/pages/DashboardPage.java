package pages;

import com.microsoft.playwright.Page;

import static org.testng.Assert.assertEquals;

public class DashboardPage extends BasePage{

//    private String locator_verificationItems = "a[href='#user/dashboard/outstandingVerificationItems']";
//    private String locator_paymentApprovals = "a[href='#user/dashboard/approvePayments']";
//    private String locator_pendingPayments = "a[href='#user/dashboard/pendingPayments']";
//    private String locator_awaitingMyApproval = "[aria-controls=approve_payments]";
//    private String locator_pendingPayables = "[aria-controls=pending_payables]";
//    private String locator_pendingPayablesHeading = "text=Payments currently in transit";
//    private String locator_paymentsApprovalsBadgeCount = "a[href='#user/dashboard/approvePayments'] > .pull-right > .badge";
//    private String locator_paymentsPendingBadgeCount = "a[href='#user/dashboard/pendingPayments'] > .pull-right > .badge";
//    private String locator_cavages = "[title=Cavages]";
//    private String locator_cavagesPaymentDetails = "text=Sent Payment Details";

    public DashboardPage(Page page){
        super(page);
    }

    public void waitForDashboard() {
        page.waitForSelector(locatorRepository.getProperty("locator_verificationItems"));
    }

    public void viewPaymentApprovals() {
        page.click(locatorRepository.getProperty("locator_paymentApprovals"));
        page.waitForSelector(locatorRepository.getProperty("locator_awaitingMyApproval"));
    }

    public void viewPendingPayments() {
        page.click(locatorRepository.getProperty("locator_pendingPayments"));
        page.waitForSelector(locatorRepository.getProperty("locator_pendingPayables"));
    }

    public String getPendingPayablesPageHeading() {
        return page.innerText(locatorRepository.getProperty("locator_pendingPayablesHeading"));
    }

    public String getPendingApprovalBadgeCount() {
        return page.innerText(locatorRepository.getProperty("locator_paymentsApprovalsBadgeCount"));
    }

    public String getPendingPaymentsBadgeCount() {
        return page.innerText(locatorRepository.getProperty("locator_paymentsPendingBadgeCount"));
    }

    public void viewCavagesPaymentsdetails() {
        page.click(locatorRepository.getProperty("locator_cavages"));
        page.waitForSelector(locatorRepository.getProperty("locator_cavagesPaymentDetails"));
    }
}