package com.stripe.StripePayment.model;


import jakarta.persistence.Embeddable;

@Embeddable
public class PaymentCurrency {
    public static enum Currency {
        USD, EUR;
    }

}
