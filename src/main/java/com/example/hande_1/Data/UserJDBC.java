package com.example.hande_1.Data;

import com.example.hande_1.Data.Article;
import com.example.hande_1.Data.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class UserJDBC {
    private long id;
    private String Nickname;
    private String Username;
    private String passWord;

    // default ="ROLE_USER"
    private String role ="ROLE_USER";
}
