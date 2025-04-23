package lk.ijse.mindwave.bo.custom.impl;

import lk.ijse.mindwave.bo.custom.TherapySessionBO;
import lk.ijse.mindwave.dao.custom.PatientDAO;
import lk.ijse.mindwave.dao.custom.TherapistDAO;
import lk.ijse.mindwave.dao.custom.TherapyProgramDAO;
import lk.ijse.mindwave.dao.custom.TherapySessionDAO;
import lk.ijse.mindwave.dao.custom.impl.PatientDAOImpl;
import lk.ijse.mindwave.dao.custom.impl.TherapistDAOImpl;
import lk.ijse.mindwave.dao.custom.impl.TherapyProgramDAOImpl;
import lk.ijse.mindwave.dao.custom.impl.TherapySessionDAOImpl;
import lk.ijse.mindwave.dto.TherapySessionDTO;
import lk.ijse.mindwave.entity.Therapist;
import lk.ijse.mindwave.entity.TherapySession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TherapySessionBOImpl implements TherapySessionBO {
    private final TherapySessionDAO sessionDAO = new TherapySessionDAOImpl();
    private final PatientDAO patientDAO = new PatientDAOImpl();
    private final TherapistDAO therapistDAO = new TherapistDAOImpl();
    private final TherapyProgramDAO programDAO = new TherapyProgramDAOImpl();

    @Override
    public boolean bookSession(String sessionId, String patientId, String therapistId, String programId, LocalDate date, LocalTime time) {
        // Check for conflicts
        List<TherapySession> existing = sessionDAO.findByTherapistAndTime(therapistId, date, time);
        if (!existing.isEmpty()) {
            System.out.println("Therapist is not available at this time.");
            return false;
        }

        TherapySession session = new TherapySession();
        session.setSessionId(sessionId);
        session.setSessionDate(date);
        session.setSessionTime(time);
        session.setStatus("BOOKED");
        session.setPatient(patientDAO.findById(patientId));
        session.setTherapist(therapistDAO.findById(therapistId));
        session.setTherapyProgram(programDAO.findById(programId));

        boolean sessionSaved = sessionDAO.save(session);
        if (!sessionSaved) {
//            System.out.println("Failed to save therapy session.");
            return false;
        }

//         Optional: Set availability manually if you want to reflect it elsewhere
        Therapist therapist = session.getTherapist();
        therapist.setAvailability("BUSY");
        therapistDAO.update(therapist); // Ensure update() exists in TherapistDAO

//        System.out.println("Session booked successfully.");
        return true;
    }

    @Override
    public ArrayList<TherapySessionDTO> loadAllSessions() {
        ArrayList<TherapySessionDTO> sessionDTOS = new ArrayList<>();
        ArrayList<TherapySession> sessions = (ArrayList<TherapySession>) sessionDAO.getAll();

        for (TherapySession session : sessions) {
            sessionDTOS.add(
                    new TherapySessionDTO(
                            session.getSessionId(),
                            session.getSessionDate(),
                            session.getSessionTime(),
                            session.getStatus(),
                            session.getPatient().getId(),
                            session.getTherapyProgram().getProgramId(),
                            session.getTherapist().getTherapistID()
                    ));

        }
        return sessionDTOS;
    }
}
