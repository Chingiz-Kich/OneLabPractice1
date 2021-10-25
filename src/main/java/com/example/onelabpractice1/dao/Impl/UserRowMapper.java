package com.example.onelabpractice1.dao.Impl;

import com.example.onelabpractice1.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setPhoneNumber(rs.getString("phone_number"));

        return user;
    }
}
