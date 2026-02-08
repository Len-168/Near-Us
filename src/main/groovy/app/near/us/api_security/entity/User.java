package app.near.us.api_security.entity;

import app.near.us.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User extends BaseEntity {

    @Column(name = "PHONE_NUMBER" , nullable = false , length = 15)
    private String phoneNumber;
    @Column(name = "USERNAME" , nullable = false , length = 50)
    private String username;
    @Column(name = "PASSWORD" , nullable = false , length = 100)
    private String password;
    @Column(name = "ROLE" ,nullable = false)
    private String role;
    @Column(name = "ENABLED" ,nullable = false)
    private boolean enabled = true;

}
