package com.plooto.endtoend;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.CompanySelectionPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ViewPaymentDetailsPage;
import sampledata.SampleData;
import util.JsonUtil;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;


public class EndToEndTests extends BaseTests {

    protected CompanySelectionPage companySelectionPage;
    protected DashboardPage dashboardPage;
    protected ViewPaymentDetailsPage viewPaymentDetailsPage;

    @Test
    public void testCavagesPaymentDetailsTest(){
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
        dashboardPage.viewCavagesPaymentsdetails();
        viewPaymentDetailsPage = new ViewPaymentDetailsPage(page);
        System.out.println(viewPaymentDetailsPage.getRecentTransactionsCount());
        viewPaymentDetailsPage.getRecentTransactionsDetails()
                .forEach(e -> System.out.println(e.toString()));
        assertEquals(viewPaymentDetailsPage.getPaymentDetailsVisibleButtonsText().size(), 3,
                "More buttons are visible than expected. " + viewPaymentDetailsPage.getPaymentDetailsVisibleButtonsText().toString());
        assertTrue(viewPaymentDetailsPage.getPaymentDetailsVisibleButtonsText().contains(" Export PDF"));
        assertTrue(viewPaymentDetailsPage.getPaymentDetailsVisibleButtonsText().contains(" Edit"));
        assertTrue(viewPaymentDetailsPage.getPaymentDetailsVisibleButtonsText().contains(" Delete"));
        log.info(JsonUtil.getJson(viewPaymentDetailsPage.getPaymentDetails()));
        assertEquals(JsonUtil.getJson(viewPaymentDetailsPage.getPaymentDetails()), SampleData.CAVAGES_PAYMENT_DETAILS_JSON);
     }
}
