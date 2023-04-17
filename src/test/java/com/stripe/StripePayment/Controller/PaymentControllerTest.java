package com.stripe.StripePayment.Controller;

import com.stripe.StripePayment.controller.PaymentController;
import com.stripe.StripePayment.dto.PaymentDto;
import com.stripe.StripePayment.model.PaymentCurrency;
import com.stripe.StripePayment.service.IPaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PaymentControllerTest {

    @Mock
    IPaymentService paymentService;

    @InjectMocks
    PaymentController paymentController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void paymentTest() throws StripeException {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(1000);
        paymentDto.setCurrency(PaymentCurrency.Currency.USD);
        paymentDto.setDescription("Test payment");

        PaymentIntent paymentIntent = new PaymentIntent();
        paymentIntent.setId("test_id");
        paymentIntent.setAmount(Long.valueOf(paymentDto.getAmount()));


        paymentIntent.setCurrency(paymentDto.getCurrency().toString());

        when(paymentService.paymentIntent(paymentDto)).thenReturn(paymentIntent);

        ResponseEntity<String> responseEntity = paymentController.payment(paymentDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(paymentIntent.toJson(), responseEntity.getBody());
    }

    @Test
    public void confirmTest() throws StripeException {
        String paymentId = "test_id";

        PaymentIntent paymentIntent = new PaymentIntent();
        paymentIntent.setId(paymentId);
        paymentIntent.setStatus("succeeded");

        when(paymentService.confirm(paymentId)).thenReturn(paymentIntent);

        ResponseEntity<String> responseEntity = paymentController.confirm(paymentId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(paymentIntent.toJson(), responseEntity.getBody());
    }

    @Test
    public void cancelTest() throws StripeException {
        String paymentId = "test_id";

        PaymentIntent paymentIntent = new PaymentIntent();
        paymentIntent.setId(paymentId);
        paymentIntent.setStatus("canceled");

        when(paymentService.cancel(paymentId)).thenReturn(paymentIntent);

        ResponseEntity<String> responseEntity = paymentController.cancel(paymentId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(paymentIntent.toJson(), responseEntity.getBody());
    }
}
