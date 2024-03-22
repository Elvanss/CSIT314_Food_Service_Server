package com.management.csit314_project.Mapper;

import com.management.csit314_project.DTO.PaymentDTO;
import com.management.csit314_project.Model.Payment;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentMapper implements Converter<Payment, PaymentDTO> {

    @Override
    public PaymentDTO convert(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getPayerId(),
                payment.getPayeeId(),
                payment.getPayerName(),
                payment.getPayeename(),
                payment.getPaymentMethod(),
                payment.getAmount(),
                payment.getPaymentDateTime(),
                payment.getPaymentStatus());
    }
}
