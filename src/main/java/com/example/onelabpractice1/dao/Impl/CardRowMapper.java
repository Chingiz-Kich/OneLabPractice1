package com.example.onelabpractice1.dao.Impl;

import com.example.onelabpractice1.models.Card;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardRowMapper implements RowMapper<Card> {
    @Override
    public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
        Card card = new Card();
        card.setNumber(rs.getString("number"));
        card.setBalance(rs.getDouble("balance"));
        return card;
    }
}
