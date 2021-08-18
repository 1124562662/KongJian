package com.example.hande_1.security;

import com.example.hande_1.Data.UserJDBC;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class UserRegistrationForm {

    @NotBlank(message="Nickname is required")
    private String Nickname;
    @NotBlank(message="Username is required")
    private String Username;
    @NotBlank(message="passWord is required")
    private String passWord;

    public UserJDBC toUserJDBC (PasswordEncoder passwordEncoder){
        UserJDBC userJDBC= new UserJDBC();
        userJDBC.setUsername(this.Username);
        userJDBC.setNickname(this.Nickname);
        userJDBC.setPassWord(passwordEncoder.encode(this.passWord));
        return  userJDBC;
    }
}
