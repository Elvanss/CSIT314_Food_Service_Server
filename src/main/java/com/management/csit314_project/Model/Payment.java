package com.management.csit314_project.Model;

import com.management.csit314_project.Model.Type.PaymentMethod;
import com.management.csit314_project.Model.Type.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Payer_Id")
    private Integer payerId;

    @Column(name = "payee_Id")
    private Integer payeeId;

    @Column(name = "Payer_name")
    private String payerName;

    @Column(name = "Payee_Name")
    private String Payeename;

    @Column(name = "Payment_Method")
    private PaymentMethod paymentMethod;

    @Column(name = "Amount")
    private Long amount;

    @Column(name = "Payment_Date_Time")
    private LocalDateTime paymentDateTime;

    @Column(name = "Payment_status")
    private PaymentStatus paymentStatus;
}
