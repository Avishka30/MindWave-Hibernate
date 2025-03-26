package lk.ijse.mindwave.bo.custom.impl;

import lk.ijse.mindwave.bo.custom.UserBo;
import lk.ijse.mindwave.dao.custom.UserDAO;
import lk.ijse.mindwave.dao.custom.impl.UserDAOImpl;
import lk.ijse.mindwave.entity.User;
import lk.ijse.mindwave.util.PasswordEncryptionUtil;
import lk.ijse.mindwave.util.Role;

public class UserBoImpl implements UserBo {
    private final UserDAO userDAO = new UserDAOImpl();
    @Override
    public boolean saveUser(String fullName, String username, String email, String password, Role role) {
        if (userDAO.getUserByUsername(username) != null) {
            return false;
        }
        String hashedPassword = PasswordEncryptionUtil.hashPassword(password);
        String newUserId = userDAO.getNextId();
        User user = new User(newUserId, fullName, username, email, hashedPassword, role);
        userDAO.save(user);
        return true;
    }
}
