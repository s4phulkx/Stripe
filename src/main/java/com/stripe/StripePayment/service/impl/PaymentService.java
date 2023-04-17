package com.stripe.StripePayment.service.impl;


import com.stripe.StripePayment.dto.PaymentDto;
import com.stripe.StripePayment.dto.PaymentDto2;
import com.stripe.StripePayment.mapper.PaymentMapper;
import com.stripe.StripePayment.model.Payment;
import com.stripe.StripePayment.repository.PaymentRepository;
import com.stripe.StripePayment.service.IPaymentService;
import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stripe.exception.StripeException;

import java.util.*;

import com.stripe.model.PaymentIntent;

@Service
public class PaymentService implements IPaymentService {

    @Value("${stripe.key.secret}")
    String secretKey;
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentIntent paymentIntent(PaymentDto paymentDto) throws StripeException {
        Stripe.apiKey = secretKey;
        List<String> paymentMethodTypes = new ArrayList();
        paymentMethodTypes.add("card");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentDto.getAmount());
        params.put("currency", paymentDto.getCurrency().toString().toUpperCase());
        params.put("description", paymentDto.getDescription());
        params.put("payment_method_types", paymentMethodTypes);
        PaymentIntent paymentIntent = PaymentIntent.create(params);


        PaymentDto2 paymentDto2 = new PaymentDto2();
        paymentDto2.setPaymentStatus("Pago pendiente");
        paymentDto2.setPaymentDate(new Date());
        paymentDto2.setStripePaymentId(paymentIntent.getId());

        paymentDto2.setAmount(paymentDto.getAmount());
        paymentDto2.setCurrency(paymentDto.getCurrency());
        paymentDto2.setDescription(paymentDto.getDescription());

        Payment payment = PaymentMapper.toModel(paymentDto2);
        paymentRepository.save(payment);

        return paymentIntent;
    }

    public PaymentIntent confirm(String stripePaymentId) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(stripePaymentId);
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        paymentIntent.confirm(params);

        PaymentDto2 paymentDto = PaymentMapper.toDto(paymentRepository.findByStripePaymentId(stripePaymentId));
        paymentDto.setPaymentStatus("Pagado");
        paymentDto.setPaymentDate(new Date());
        paymentRepository.save(PaymentMapper.toModel(paymentDto));


        return paymentIntent;
    }

    public PaymentIntent cancel(String stripePaymentId) throws StripeException {
        Stripe.apiKey = secretKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(stripePaymentId);
        paymentIntent.cancel();


        PaymentDto2 paymentDto = PaymentMapper.toDto(paymentRepository.findByStripePaymentId(stripePaymentId));
        paymentDto.setPaymentStatus("Cancelado");
        paymentRepository.save(PaymentMapper.toModel(paymentDto));

        return paymentIntent;
    }
}