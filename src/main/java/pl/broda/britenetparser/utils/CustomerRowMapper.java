package pl.broda.britenetparser.utils;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pl.broda.britenetparser.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();

        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setSurname(rs.getString("surname"));
        customer.setAge(rs.getInt("age"));

        return customer;
    }

}
