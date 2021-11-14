package model;

import lombok.Data;

@Data
public class RecentTransactions {
    String contact;
    String status;
    String requestedDebitDate;
    String completedDate;
    RecentTransactionsAmount amount;
}
