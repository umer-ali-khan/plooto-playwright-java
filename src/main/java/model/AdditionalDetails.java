package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdditionalDetails {
    String createdDate;
    String payTo;
    String payFrom;
}
