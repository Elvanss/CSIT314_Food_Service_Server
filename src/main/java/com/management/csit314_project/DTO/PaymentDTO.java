package com.management.csit314_project.DTO;

import com.management.csit314_project.Model.Type.PaymentMethod;
import com.management.csit314_project.Model.Type.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Integer Id;
    private Integer payerId;
    private Integer payeeId;
    private String PayerName;
    private String PayeeName;
    private PaymentMethod paymentMethod;
    private Long amount;
    private LocalDateTime paymentDateTime;
    private PaymentStatus paymentStatus;
}
