package model;

import lombok.Data;

import java.util.List;

@Data
public class ViewPaymentDetails {
    SentPaymentDetails sentPaymentDetails;
    PaymentAttachments paymentAttachments;
    String approvalHistory;
    String paymentLines;
    String auditTrail;
    AdditionalDetails additionalDetails;
    List<RecentTransactions> recentTransactions;
}
