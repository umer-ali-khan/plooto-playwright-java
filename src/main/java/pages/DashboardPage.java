package pages;

import com.microsoft.playwright.Page;

import static org.testng.Assert.assertEquals;

public class DashboardPage {

    private Page page;

    private String locator_verificationItems = "a[href='#user/dashboard/outstandingVerificationItems']";
    private String locator_paymentApprovals = "a[href='#user/dashboard/approvePayments']";
    private String locator_pendingPayments = "a[href='#user/dashboard/pendingPayments']";
    private String locator_awaitingMyApproval = "[aria-controls=approve_payments]";
    private String locator_pendingPayables = "[aria-controls=pending_payables]";
    private String locator_pendingPayablesHeading = "text=Payments currently in transit";
    private String locator_paymentsApprovalsBadgeCount = "a[href='#user/dashboard/approvePayments'] > .pull-right > .badge";
    private String locator_paymentsPendingBadgeCount = "a[href='#user/dashboard/approvePayments'] > .pull-right > .badge";
    private String locator_cavages = "[title=Cavages]";
    private String locator_cavagesPaymentDetails = "text=Sent Payment Details";

    public DashboardPage(Page page){
        this.page = page;
    }

    public void waitForDashboard() {
        page.waitForSelector(locator_verificationItems);
    }

    public void viewPaymentApprovals() {
        page.click(locator_paymentApprovals);
        page.waitForSelector(locator_awaitingMyApproval);
    }

    public void viewPendingPayments() {
        page.click(locator_pendingPayments);
        page.waitForSelector(locator_pendingPayables);
    }

    public String getPendingPayablesPageHeading() {
        return page.innerText(locator_pendingPayablesHeading);
    }

    public String getPendingApprovalBadgeCount() {
        return page.innerText(locator_paymentsApprovalsBadgeCount);
    }

    public String getPendingPaymentsBadgeCount() {
        return page.innerText(locator_paymentsPendingBadgeCount);
    }

    public void clickCavages() {
        page.click(locator_cavages);
        page.waitForSelector(locator_cavagesPaymentDetails);
    }
}