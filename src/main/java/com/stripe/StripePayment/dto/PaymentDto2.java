package com.stripe.StripePayment.dto;



import com.stripe.StripePayment.model.PaymentCurrency;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaymentDto2 {



    private long idPayments;
    private String paymentStatus;
    private Date paymentDate;
    private String stripePaymentId;

    public static PaymentCurrency.Currency fromString(String value) {
        return PaymentCurrency.Currency.valueOf(value);
    }


    private String description;
    private int amount;
    private PaymentCurrency.Currency currency;


}
