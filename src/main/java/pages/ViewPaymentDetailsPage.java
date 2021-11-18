package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewPaymentDetailsPage extends BasePage{

//    private String locator_recentTransactions = ".editRow.h-du-8";

    public ViewPaymentDetailsPage(Page page){
        super(page);
    }

    public void waitForPaymentDetails() {
        page.waitForSelector(locatorRepository.getProperty("locator_recentTransactions"));
    }

    public int getRecentTransactionsCount() {
        return page.locator(locatorRepository.getProperty("locator_recentTransactions")).count();
    }

    public List<RecentTransactions> getRecentTransactionsDetails() {
        List<RecentTransactions> recentTransactionsList = new ArrayList<>();
        page.querySelectorAll(locatorRepository.getProperty("locator_recentTransactions"))
                .forEach(
                        transaction -> {
                            RecentTransactions recentTransaction = new RecentTransactions();
                            List<String> innerTexts =
                            transaction.querySelectorAll("td")
                                    .stream()
                                    .map(ElementHandle::innerText)
                                    .collect(Collectors.toList());
                            recentTransaction.setContact(innerTexts.get(0));
                            recentTransaction.setStatus(innerTexts.get(1));
                            recentTransaction.setRequestedDebitDate(innerTexts.get(2));
                            recentTransaction.setCompletedDate(innerTexts.get(3));
                            RecentTransactionsAmount amount = new RecentTransactionsAmount();
                            amount.setAmountDetails(innerTexts.get(4).split("\\n")[0]);
                            amount.setAmountInUSD(innerTexts.get(4).split("\\n")[1]);
                            recentTransaction.setAmount(amount);
                            recentTransactionsList.add(recentTransaction);
                        }
                );
        return recentTransactionsList;
    }

    public  List<String> getPaymentDetailsVisibleButtonsText() {
        return page.querySelectorAll(locatorRepository.getProperty("locator_viewPaymentDetails_visibleButtonsAtTop"))
                .stream()
                .map(ElementHandle::innerText)
                .collect(Collectors.toList());
    }

    private  List<String> getPaymentDetailsValues() {
        return page.querySelectorAll(locatorRepository.getProperty("locator_viewPaymentDetails_valueColumns"))
                .stream()
                .map(ElementHandle::innerText)
                .collect(Collectors.toList());
    }

    public ViewPaymentDetails getPaymentDetails() {
        ViewPaymentDetails paymentDetails = new ViewPaymentDetails();
        paymentDetails.setRecentTransactions(getRecentTransactionsDetails());
        List<String> paymentDetailsValues = getPaymentDetailsValues();
        paymentDetails.setSentPaymentDetails(new SentPaymentDetails(
                paymentDetailsValues.get(0),
                paymentDetailsValues.get(1),
                paymentDetailsValues.get(2),
                paymentDetailsValues.get(3),
                paymentDetailsValues.get(4),
                paymentDetailsValues.get(5),
                paymentDetailsValues.get(6)));
        paymentDetails.setPaymentAttachments(new PaymentAttachments(paymentDetailsValues.get(7)));
        paymentDetails.setAdditionalDetails(new AdditionalDetails(
                paymentDetailsValues.get(8),
                paymentDetailsValues.get(9),
                paymentDetailsValues.get(10)
        ));
        List<String> approvalAndPaymentLinesText = page.querySelectorAll(locatorRepository.getProperty("locator_viewPaymentDetails_approvalAndPaymentLines"))
                .stream()
                .map(ElementHandle::innerText)
                .collect(Collectors.toList());
        paymentDetails.setApprovalHistory(approvalAndPaymentLinesText.get(0));
        paymentDetails.setPaymentLines(approvalAndPaymentLinesText.get(1));
        paymentDetails.setAuditTrail(page.innerText(locatorRepository.getProperty("locator_viewPaymentDetails_auditTrail")));
        return paymentDetails;
    }
}