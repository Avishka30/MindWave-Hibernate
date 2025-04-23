package lk.ijse.mindwave.dao.custom;

import java.util.List;

public interface CrudDAO <T> {
    boolean save(T entity);
    boolean update(T entity);
    boolean deleteByPK(String id) throws Exception;
    String getNextId();
    List<T> getAll();
    T findById(String id) ;

}
