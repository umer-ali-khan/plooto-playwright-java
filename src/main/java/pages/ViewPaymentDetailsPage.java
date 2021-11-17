package pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import model.RecentTransactions;
import model.RecentTransactionsAmount;

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
}