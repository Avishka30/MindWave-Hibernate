package lk.ijse.mindwave.entity;

import jakarta.persistence.*;
import lk.ijse.mindwave.util.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User_table")
public class User {
    @Id
    private String userId;
    private String fullName;
    private String userName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
   private Role role;

}
