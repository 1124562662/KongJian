package com.example.hande_1.Data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Article {


    private  long Id;
    private Date createTime;
    private String title;
    private String content;

    private ContentType contentType;
    public enum ContentType {Article, SmallTalk}



}
