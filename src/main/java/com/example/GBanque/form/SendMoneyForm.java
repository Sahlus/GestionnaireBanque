package com.example.GBanque.form;


import lombok.Data;

@Data
public class SendMoneyForm {

    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;
    public SendMoneyForm(Long fromAccountId, Long toAccountId, Double amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }




}