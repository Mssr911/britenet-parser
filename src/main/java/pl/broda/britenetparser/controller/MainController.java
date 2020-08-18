package pl.broda.britenetparser.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.broda.britenetparser.model.Customer;
import pl.broda.britenetparser.utils.ContactRowMapper;
import pl.broda.britenetparser.utils.CustomerRowMapper;

import java.util.List;

@RestController
public class MainController {

    JdbcTemplate jdbcTemplate;
    ContactRowMapper contactRowMapper;
    CustomerRowMapper customerRowMapper;

    public MainController(JdbcTemplate jdbcTemplate, ContactRowMapper contactRowMapper, CustomerRowMapper customerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.contactRowMapper = contactRowMapper;
        this.customerRowMapper = customerRowMapper;
    }

    @GetMapping("/test")
    public List<Customer> test() {
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM CUSTOMERS WHERE ROWNUM = 1", customerRowMapper);
        return customers;
    }
}