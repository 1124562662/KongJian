package com.example.hande_1.dao;

import com.example.hande_1.Data.Article;
import com.example.hande_1.Data.UserJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class ArticleRepository {
   private JdbcTemplate jdbcTemplate;
   @Autowired
    ArticleRepository(JdbcTemplate jdbcTemplate){
       this.jdbcTemplate =jdbcTemplate;
   }


    public Article save_Article (Article article ){
        String InsertSql = "insert into article (createTime, title, content, ContentType) values (?,?,?,?)";
        PreparedStatementCreatorFactory preparedStatementCreatorFactory=
                new PreparedStatementCreatorFactory( InsertSql, Types.TIMESTAMP , Types.VARCHAR, Types.BLOB,Types.VARCHAR);

        // Must open to get aotu-increment id value
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator pc =
                preparedStatementCreatorFactory.newPreparedStatementCreator(
                        Arrays.asList(new Timestamp(article.getCreateTime().getTime()),
                                article.getTitle(),article.getContent(),article.getContentType().toString() ));

        KeyHolder key = new GeneratedKeyHolder();
        this.jdbcTemplate.update(pc,key);
        // returned key
        Long k= key.getKey().longValue();
        article.setId(k);
        return article;
    }

    public Iterable<Article> LoadArticleByTitle(String title){
        String sql = "select * from article where title = ?";
        return this.jdbcTemplate.query(sql,this::MapRowToArticle,title);
    }

    public  Article LoadArticleById (long id){
        String sql = "select * from article where id = ?";
        return this.jdbcTemplate.queryForObject(sql,this::MapRowToArticle,id);
    }

    public Iterable<Article>  GetLatest(Article.ContentType contentType, int num){
        String sql = "select * from article where ContentType = "+contentType.toString()+" order by createTime DESC  limit"+num ;
        return jdbcTemplate.query(sql, this::MapRowToArticle);
    }

    private Article MapRowToArticle(ResultSet resultSet, int RowNumber) throws SQLException {
       Article article= new Article();
       article.setId(Long.parseLong(resultSet.getString("id")));
       article.setCreateTime(new Date( resultSet.getString("createTime")));
       article.setContent(resultSet.getString("content"));
       article.setContentType(Article.ContentType.valueOf(resultSet.getString("ContentType")));
       return article;
    }







}
