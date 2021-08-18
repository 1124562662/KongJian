package com.example.hande_1.Data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Comment {
    private  long Id;
    private Date createTime;
    private String content;

}
