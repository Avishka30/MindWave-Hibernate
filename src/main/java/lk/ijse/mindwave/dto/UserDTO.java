package lk.ijse.mindwave.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
    private String role;

}
