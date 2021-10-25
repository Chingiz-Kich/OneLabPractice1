package com.example.onelabpractice1.dao.Impl;

import com.example.onelabpractice1.aspects.GetInfoBeforeExecute;
import com.example.onelabpractice1.dao.TransferDao;
import com.example.onelabpractice1.models.Transfer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransferDaoImpl implements TransferDao, InitializingBean {

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

    @GetInfoBeforeExecute
    @Override
    public void saveTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfers (sender_phone_number, recipient_phone_number, money, date) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, transfer.getSenderPhoneNumber(), transfer.getRecipientPhoneNumber(), transfer.getMoney(), transfer.getTransferDate());
    }

    @Override
    public List<Transfer> getAll() {
        return jdbcTemplate.query("SELECT * FROM transfers", new TransferRowMapper());
    }
}
