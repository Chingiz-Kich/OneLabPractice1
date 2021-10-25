package com.example.onelabpractice1.dao.Impl;

import com.example.onelabpractice1.dao.UserDao;
import com.example.onelabpractice1.models.User;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao, InitializingBean {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null) {
            throw new BeanCreationException("Jdbc Template is null");
        }
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users (name, surname, phone_number) values (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getSurname(),
                user.getPhoneNumber());
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return jdbcTemplate.query("SELECT * FROM users WHERE phone_number=?", new UserRowMapper(), phoneNumber)
                .stream().findAny().orElse(null);
    }
}
