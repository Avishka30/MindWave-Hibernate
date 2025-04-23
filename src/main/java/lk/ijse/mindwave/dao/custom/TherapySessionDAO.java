package lk.ijse.mindwave.dao.custom;

import lk.ijse.mindwave.entity.TherapySession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TherapySessionDAO extends CrudDAO<TherapySession> {
    List<TherapySession> findByTherapistAndTime(String therapistId, LocalDate date, LocalTime time) ;
}
