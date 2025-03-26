package lk.ijse.mindwave.bo.custom;

import lk.ijse.mindwave.util.Role;

public interface UserBo {
    boolean saveUser(String fullName, String username, String email, String password, Role role);
}
