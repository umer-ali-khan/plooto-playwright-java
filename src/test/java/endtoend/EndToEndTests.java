package search;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.CompanySelectionPage;
import pages.DashboardPage;
import pages.LoginPage;

import java.util.List;

import static org.testng.Assert.*;

public class EndToEndTests extends BaseTests {

    protected CompanySelectionPage companySelectionPage;
    protected DashboardPage dashboardPage;

    @Test
    public void loginAndNavigate(){
        loginPage.login("user@user.com", "pwd");
        companySelectionPage = new CompanySelectionPage(page);
        dashboardPage = new DashboardPage(page);
        companySelectionPage.selectPlooto();
        dashboardPage.waitForDashboard();
        dashboardPage.viewPaymentApprovals();
        dashboardPage.viewPendingPayments();
        assertEquals(dashboardPage.getPendingPayablesPageHeading(), "Payments currently in transit");
        dashboardPage.viewPaymentApprovals();
        assertEquals(dashboardPage.getPendingApprovalBadgeCount(), "1");
        assertEquals(dashboardPage.getPendingPaymentsBadgeCount(), "39");
        dashboardPage.viewPendingPayments();
        dashboardPage.clickCavages();

     }
}
