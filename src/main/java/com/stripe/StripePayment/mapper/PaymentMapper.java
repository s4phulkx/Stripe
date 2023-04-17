package com.stripe.StripePayment.mapper;

import com.stripe.StripePayment.dto.PaymentDto2;
import com.stripe.StripePayment.model.Payment;
import com.stripe.StripePayment.model.PaymentCurrency;

public class PaymentMapper {

    public static PaymentDto2 toDto(Payment payment) {
        PaymentDto2 paymentDto = new PaymentDto2();
        paymentDto.setIdPayments(payment.getIdPayments());
        paymentDto.setPaymentStatus(payment.getPaymentStatus());
        paymentDto.setPaymentDate(payment.getPaymentDate());
        paymentDto.setStripePaymentId(payment.getStripePaymentId());
        paymentDto.setAmount(payment.getAmount());
        paymentDto.setDescription(payment.getDescription());
        paymentDto.setCurrency(payment.getCurrency());
        return paymentDto;
    }

    public static Payment toModel(PaymentDto2 paymentDto) {
        Payment payment = new Payment();
        payment.setIdPayments(paymentDto.getIdPayments());
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        payment.setStripePaymentId(paymentDto.getStripePaymentId());
        payment.setAmount(paymentDto.getAmount());
        payment.setDescription(paymentDto.getDescription());
        if (paymentDto.getCurrency() != null) {
            payment.setCurrency(PaymentCurrency.Currency.valueOf(paymentDto.getCurrency().name()));
        }
        return payment;
    }

}





