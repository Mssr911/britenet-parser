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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CustomerRepository {

    JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //    INSERT SINGLE CUSTOMER AND RETURN HIS ID
    public int insertCustomer(Customer customer) {
        String sql = "insert into CUSTOMERS (name, surname, age) " + "values(?,  ?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSurname());
            if (customer.getAge() != null) {
                ps.setInt(3, customer.getAge());
            } else {
                ps.setNull(3, 1);
            }
            return ps;
        }, holder);

        return Objects.requireNonNull(holder.getKey()).intValue();
    }

    //    INSERT ALL CONTACTS DECLARED FOR SINGLE CUSTOMER.
    //    METHOD WORKS ONLY WITH XML PARSER
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
                ps.setInt(2, Optional.ofNullable(Contact.CONTACT_TYPES.get(element.getTagName()))
                        .orElse(0));
                ps.setString(3, element.getTextContent());

            }

            @Override
            public int getBatchSize() {
                return contacts.getContent().size();
            }
        });

    }

    //    INSERT ALL CONTACTS DECLARED FOR SINGLE CUSTOMER.
    //    METHOD WORKS ONLY WITH CSV PARSER
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

    //    CHECH WHICH INSERT METHOD SHOULD BE USED AND INSERT ALL GIVEN CUSTOMERS
    public void insertCustomers(Customers customers) {
        for (Customer customer : customers.getCustomerList()) {
            int id = insertCustomer(customer);
            if (customer.getContacts().getCsvContent().isEmpty()) {
                insertContactforXml(customer.getContacts(), id);
            } else if (!customer.getContacts().getCsvContent().isEmpty()) {
                insertContactforCsv(customer.getContacts(), id);
            }

        }
    }
}
