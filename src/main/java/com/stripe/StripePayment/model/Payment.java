package com.stripe.StripePayment.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;



@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payments")
    private long idPayments;


    @Column(name = "payment_status")
    @NotNull
    private String paymentStatus;

    @Column(name = "payment_date")
    @NotNull
    private Date paymentDate;

    @Column(name = "stripe_payment_id")
    @NotNull
    private String stripePaymentId;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "amount")
    @NotNull
    private int amount;



    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    @NotNull
    private PaymentCurrency.Currency currency;


}

