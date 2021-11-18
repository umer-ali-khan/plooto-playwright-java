package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SentPaymentDetails {
    String contact;
    String amount;
    String exchangeRate;
    String requestedDebitDate;
    String status;
    String initiatedBy;
    String estimatedCreditDate;
}
