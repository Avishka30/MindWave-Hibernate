package lk.ijse.mindwave.bo;

import lk.ijse.mindwave.dao.custom.UserDAO;
import lk.ijse.mindwave.dao.custom.impl.UserDAOImpl;
import lk.ijse.mindwave.entity.User;
import lk.ijse.mindwave.util.PasswordEncryptionUtil;
import lk.ijse.mindwave.util.Role;

public class AuthService {
    private final UserDAO userDAO = new UserDAOImpl();

    public Role authenticate(String username, String password) {
        User user = userDAO.getUserByUsername(username);
      if (user != null && PasswordEncryptionUtil.checkPassword(password, user.getPassword())) {
          return user.getRole();
      }
      return null;
    }
}
