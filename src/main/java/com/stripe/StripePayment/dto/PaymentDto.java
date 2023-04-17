package com.stripe.StripePayment.dto;


import com.stripe.StripePayment.model.PaymentCurrency;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {


    private String description;
    private int amount;
    private PaymentCurrency.Currency currency;








}