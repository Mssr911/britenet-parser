package pl.broda.britenetparser.repository;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Element;
import pl.broda.britenetparser.model.Contact;
import pl.broda.britenetparser.model.Customer;
import pl.broda.britenetparser.model.Customers;
import pl.broda.britenetparser.utils.CustomerRowMapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class CustomerRepository {

    JdbcTemplate jdbcTemplate;
    CustomerRowMapper customerRowMapper;

    public CustomerRepository(CustomerRowMapper customerRowMapper, JdbcTemplate jdbcTemplate) {
        this.customerRowMapper = customerRowMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertCustomer(Customer customer) {
        String sql = "insert into CUSTOMERS (name, surname, age) " + "values(?,  ?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            if(customer.getAge() != null) {
                ps.setInt(3, customer.getAge());
            } else {
                ps.setNull(3, 1);
            }
            return ps;
        }, holder);

        return Objects.requireNonNull(holder.getKey()).intValue();
    }

    public void insertCustomerList(final List<Customer> customerList) {

        String query = "INSERT INTO "
                + "CUSTOMERS "
                + "(name,surname,age) "
                + "VALUES " + "(?,?,?)";

        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {

                Customer customer = customerList.get(i);
                ps.setString(1, customer.getName());
                ps.setString(2, customer.getSurname());
                ps.setInt(3, customer.getAge());

            }

            @Override
            public int getBatchSize() {
                return customerList.size();
            }
        });

    }

    public void insertContactforXml(Contact contacts, Integer customerId) {

        String query = "INSERT INTO "
                + "CONTACTS "
                + "(id_customer,type,contact) "
                + "VALUES " + "(?,?,?)";

        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {

                Object newContact = contacts.getContent().get(i);
                Element element = (Element) newContact;
                ps.setInt(1, customerId);
                ps.setString(2, element.getTagName());
                ps.setString(3, element.getTextContent());

            }

            @Override
            public int getBatchSize() {
                return contacts.getContent().size();
            }
        });

    }

    public void insertContactforCsv(Contact contacts, Integer customerId) {

        String query = "INSERT INTO "
                + "CONTACTS "
                + "(id_customer,type,contact) "
                + "VALUES " + "(?,?,?)";

        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {

                String[] newContact = contacts.getCsvContent().get(i);
                ps.setInt(1, customerId);
                ps.setInt(2, Integer.parseInt(newContact[0]));
                ps.setString(3, newContact[1]);

            }

            @Override
            public int getBatchSize() {
                return contacts.getCsvContent().size();
            }
        });

    }

    public void insertCustomers(Customers customers) {
        for (Customer customer : customers.getCustomerList()) {
            int id = insertCustomer(customer);
            insertContactforXml(customer.getContacts(), id);
            insertContactforCsv(customer.getContacts(), id);

        }
    }
}
