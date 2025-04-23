package lk.ijse.mindwave.bo.custom.impl;

import lk.ijse.mindwave.bo.custom.PaymentBO;
import lk.ijse.mindwave.dao.custom.PatientDAO;
import lk.ijse.mindwave.dao.custom.PaymentDAO;
import lk.ijse.mindwave.dao.custom.TherapySessionDAO;
import lk.ijse.mindwave.dao.custom.impl.PatientDAOImpl;
import lk.ijse.mindwave.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.mindwave.dao.custom.impl.TherapySessionDAOImpl;
import lk.ijse.mindwave.dto.PaymentDTO;
import lk.ijse.mindwave.entity.Patient;
import lk.ijse.mindwave.entity.Payment;
import lk.ijse.mindwave.entity.TherapySession;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    private final PaymentDAO paymentDAO = new PaymentDAOImpl();
    private final TherapySessionDAO therapySessionDAO = new TherapySessionDAOImpl();
    private final PatientDAO patientDAO = new PatientDAOImpl();

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) {
        Patient patient = patientDAO.findById(paymentDTO.getPatientId());
        TherapySession session = therapySessionDAO.findById(paymentDTO.getSessionId());

        return paymentDAO.save(new Payment(
                paymentDTO.getPaymentId(),
                paymentDTO.getAmount(),
                paymentDTO.getPaymentDate(),
                paymentDTO.getStatus(),
                patient,
                session
        ));
    }

    @Override
    public String getNextPaymentID() {
        return paymentDAO.getNextId();
    }

    @Override
    public ArrayList<PaymentDTO> loadAllPayments() {
        ArrayList<PaymentDTO> paymentDTOList = new ArrayList<>();
        try {
            List<Payment> paymentList = paymentDAO.getAll();

            for (Payment payment : paymentList) {
                String sessionId = (payment.getSession() != null) ? payment.getSession().getSessionId() : "N/A";

                PaymentDTO dto = new PaymentDTO(
                        payment.getPaymentId(),
                        payment.getAmount(),
                        payment.getPaymentDate(),
                        payment.getStatus(),
                        payment.getPatient().getId(),
                        sessionId
                );

                paymentDTOList.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return paymentDTOList;
    }
}
