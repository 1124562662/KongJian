package com.example.hande_1.dao;

import com.example.hande_1.Data.UserJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public UserJDBC save_UserJDBC (UserJDBC userJDBC ){
        String InsertSql = "insert into theUser (Nickname, Username, passWord,userRole) values (?,?,?,?)";
        PreparedStatementCreatorFactory preparedStatementCreatorFactory=
          new PreparedStatementCreatorFactory( InsertSql, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR);

        // Must open to get aotu-increment id value
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator pc =
         preparedStatementCreatorFactory.newPreparedStatementCreator(
                 Arrays.asList(userJDBC.getNickname(),userJDBC.getUsername(),userJDBC.getPassWord(),userJDBC.getRole()));

        KeyHolder key = new GeneratedKeyHolder();
        this.jdbcTemplate.update(pc,key);

        // returned key
        Long k= key.getKey().longValue();
        userJDBC.setId(k);
         return userJDBC;
    }

    public UserJDBC LoadUserByUsername (String username ){
        String sql = "select * from theUser where Username = ?";
        return this.jdbcTemplate.queryForObject(sql,this::MapRowToUserJDBC,username);
    }

    public UserJDBC LoadUserByNickname (String nickName ){
        String sql = "select * from theUser where Nickname = ?";
        return this.jdbcTemplate.queryForObject(sql,this::MapRowToUserJDBC,nickName);
    }

    public UserJDBC LoadUserById (long id ){
        String sql = "select * from theUser where id = ?";
        return this.jdbcTemplate.queryForObject(sql,this::MapRowToUserJDBC,id);
    }



    private UserJDBC MapRowToUserJDBC(ResultSet resultSet, int RowNumber) throws SQLException{
        UserJDBC userJDBC = new UserJDBC();
        userJDBC.setUsername(  resultSet.getString("Username" ) );
        userJDBC.setPassWord(  resultSet.getString("passWord" ));
        userJDBC.setNickname(resultSet.getString("Nickname"));
        userJDBC.setRole(resultSet.getString("userRole"));
        userJDBC.setId(Long.parseLong(resultSet.getString("id")));
        return  userJDBC;
    }


}
