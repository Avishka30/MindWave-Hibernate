package lk.ijse.mindwave.bo.custom;

import lk.ijse.mindwave.dto.PaymentDTO;

import java.util.ArrayList;

public interface PaymentBO {
    boolean savePayment(PaymentDTO paymentDTO);
    String getNextPaymentID();
    ArrayList<PaymentDTO> loadAllPayments();
}
