package com.example.hande_1.dao;

import com.example.hande_1.Data.Comment;
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

@Repository
public class CommentRepository {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    CommentRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }



    public Comment save_Comment (Comment comment ){
        String InsertSql = "insert into comment (createTime, content) values (?,?)";
        PreparedStatementCreatorFactory preparedStatementCreatorFactory=
                new PreparedStatementCreatorFactory( InsertSql, Types.TIMESTAMP,Types.BLOB);

        // Must open to get aotu-increment id value
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator pc =
                preparedStatementCreatorFactory.newPreparedStatementCreator(
                        Arrays.asList(new Timestamp(comment.getCreateTime().getTime()), comment.getContent()));

        KeyHolder key = new GeneratedKeyHolder();
        this.jdbcTemplate.update(pc,key);

        // returned key
        Long k= key.getKey().longValue();
        comment.setId(k);
        return comment;
    }

    public Comment LoadCommentById(long id){
        String sql = "select * from comment where id = ? ";
       return this.jdbcTemplate.queryForObject(sql,this::mapper,id);
    }

    private Comment mapper (ResultSet resultSet , int row) throws SQLException{
        Comment comment = new Comment();
        comment.setId(Long.parseLong(resultSet.getString("id")));
        comment.setContent(resultSet.getString("content"));
        comment.setCreateTime(new Date( resultSet.getString("createTime")));
        return comment;
    }






}
