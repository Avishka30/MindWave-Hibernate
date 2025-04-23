package lk.ijse.mindwave.dao.custom.impl;

import lk.ijse.mindwave.bo.exception.DuplicateException;
import lk.ijse.mindwave.bo.exception.NotFoundException;
import lk.ijse.mindwave.config.FactoryConfiguration;
import lk.ijse.mindwave.dao.custom.TherapistDAO;
import lk.ijse.mindwave.entity.Therapist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {
    private final FactoryConfiguration factoryConfiguration = new FactoryConfiguration();
    @Override
    public boolean save(Therapist therapist) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            //T00-001
            Therapist existsTherapist = session.get(Therapist.class, therapist.getTherapistID());
            if (existsTherapist != null) {
                throw new DuplicateException("Therapist id duplicated");
            }

            session.persist(therapist);//save
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean update(Therapist therapist) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(therapist);//update
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean deleteByPK(String id) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Therapist therapist = session.get(Therapist.class, id);
            if (therapist == null) {
                throw new NotFoundException("Therapist not found");
            }
            session.remove(therapist);
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public String getNextId() {
        Session session = factoryConfiguration.getSession();
        // Get the last user ID from the database
        String lastId = session.createQuery("SELECT t.id FROM Therapist t ORDER BY t.id DESC", String.class)
                .setMaxResults(1)
                .uniqueResult();

        if (lastId != null) {
            int numericPart = Integer.parseInt(lastId.split("-")[1]) + 1;
            return String.format("T00-%03d", numericPart);
        } else {
            return "T00-001"; // First user ID
        }
    }

    @Override
    public List<Therapist> getAll() {
        Session session = factoryConfiguration.getSession();
        Query<Therapist> query = session.createQuery("FROM Therapist", Therapist.class);
        ArrayList<Therapist> therapists = (ArrayList<Therapist>) query.list();
        return therapists;
    }

    @Override
    public Therapist findById(String id) {
        Session session = factoryConfiguration.getSession();
        Therapist therapist = session.get(Therapist.class, id);
        session.close();
        return therapist;
    }
}
