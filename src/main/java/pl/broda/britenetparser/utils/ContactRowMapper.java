package pl.broda.britenetparser.utils;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pl.broda.britenetparser.model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();

        contact.setId(rs.getInt("id"));
        contact.setIdCustomer(rs.getInt("id_customer"));
        contact.setType(rs.getInt("type"));
        contact.setContact(rs.getString("contact"));

        return contact;
    }
}
