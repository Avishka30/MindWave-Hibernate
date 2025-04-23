package lk.ijse.mindwave.dao.custom.impl;

import lk.ijse.mindwave.config.FactoryConfiguration;
import lk.ijse.mindwave.dao.custom.TherapySessionDAO;
import lk.ijse.mindwave.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TherapySessionDAOImpl implements TherapySessionDAO {
    private final FactoryConfiguration factoryConfiguration = new FactoryConfiguration();
    @Override
    public boolean save(TherapySession entity) {
        Session s = factoryConfiguration.getSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(entity);
        tx.commit();
        s.close();
        return true;
    }

    @Override
    public boolean update(TherapySession entity) {
        return false;
    }

    @Override
    public boolean deleteByPK(String id) throws Exception {
        return false;
    }

    @Override
    public String getNextId() {
        return "";
    }

    @Override
    public List<TherapySession> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<TherapySession> query = session.createQuery("FROM TherapySession ", TherapySession.class);
        ArrayList<TherapySession> sessions = (ArrayList<TherapySession>) query.list();
        return sessions;
    }

    @Override
    public TherapySession findById(String sessionId) {
        Session s = factoryConfiguration.getSession();
        TherapySession ts = s.get(TherapySession.class, sessionId);
        s.close();
        return ts;
    }

    @Override
    public List<TherapySession> findByTherapistAndTime(String therapistId, LocalDate date, LocalTime time) {
        Session s = factoryConfiguration.getSession();
        Query<TherapySession> query = s.createQuery("FROM TherapySession WHERE therapist.therapistID = :tid AND sessionDate = :date AND sessionTime = :time", TherapySession.class);
        query.setParameter("tid", therapistId);
        query.setParameter("date", date);
        query.setParameter("time", time);
        List<TherapySession> results = query.getResultList();
        s.close();
        return results;
    }
}
