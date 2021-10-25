package com.example.onelabpractice1.dao.Impl;

import com.example.onelabpractice1.models.Transfer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferRowMapper implements RowMapper<Transfer> {
    @Override
    public Transfer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transfer transfer = new Transfer();
        transfer.setSenderPhoneNumber(rs.getString("sender_phone_number"));
        transfer.setRecipientPhoneNumber(rs.getString("recipient_phone_number"));
        transfer.setMoney(rs.getDouble("money"));
        transfer.setTransferDate(rs.getDate("date"));
        return transfer;
    }
}
