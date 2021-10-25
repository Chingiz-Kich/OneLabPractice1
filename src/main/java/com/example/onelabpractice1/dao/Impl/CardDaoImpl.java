package com.example.onelabpractice1.dao.Impl;

import com.example.onelabpractice1.dao.CardDao;
import com.example.onelabpractice1.models.Card;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardDaoImpl implements CardDao, InitializingBean {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null) {
            throw new BeanCreationException("Jdbc template is null");
        }
    }

    @Override
    public List<Card> getAll() {
        return jdbcTemplate.query("SELECT * FROM cards", new CardRowMapper());
    }

    @Override
    public Card getByCardNumber(String number) {
        return jdbcTemplate.query("SELECT * FROM cards WHERE number=?", new CardRowMapper(), number)
                .stream().findAny().orElse(null);
    }

    @Override
    public void saveCard(Card card) {
        String sql = "INSERT INTO cards (number, balance) values (?, ?)";
        jdbcTemplate.update(sql, card.getNumber(), card.getBalance());
    }

    @Override
    public Card isCardExist(Card card) {
        return jdbcTemplate.query("SELECT * FROM cards WHERE number=? AND balance=?", new CardRowMapper(), card.getNumber(), card.getBalance())
                .stream().findAny().orElse(null);
    }

    @Override
    public void updateBalance(Card card) {
        jdbcTemplate.update("UPDATE cards SET balance=? WHERE number=?", card.getBalance(), card.getNumber());
    }
}
