package lk.ijse.mindwave.bo.custom;

import lk.ijse.mindwave.dto.TherapySessionDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface TherapySessionBO {
    boolean bookSession(String sessionId, String patientId, String therapistId, String programId, LocalDate date, LocalTime time);
    ArrayList<TherapySessionDTO> loadAllSessions();
}
