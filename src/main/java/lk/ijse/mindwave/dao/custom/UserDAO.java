package lk.ijse.mindwave.dao.custom;

import lk.ijse.mindwave.entity.User;

public interface UserDAO extends CrudDAO<User> {
    User getUserByUsername(String username);
}
