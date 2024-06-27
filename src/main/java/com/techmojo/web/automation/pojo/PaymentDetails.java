package com.techmojo.web.automation.pojo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PaymentDetails {
    private String payerAccount;
    private String beneficiary;
    private String beneficiaryAccount;
    private String beneficiaryBankCode;
    private String beneficiaryBankName;
    private String bankAddress;
    private String paymentAmount;
    private String paymentInfo;
}
