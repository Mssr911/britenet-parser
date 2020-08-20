package pl.broda.britenetparser.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.broda.britenetparser.model.Customer;
import pl.broda.britenetparser.model.Customers;
import pl.broda.britenetparser.service.XmlParser;
import pl.broda.britenetparser.utils.ContactRowMapper;
import pl.broda.britenetparser.utils.CustomerRowMapper;

import java.util.List;

@RestController
public class MainController {

    JdbcTemplate jdbcTemplate;
    ContactRowMapper contactRowMapper;
    CustomerRowMapper customerRowMapper;
    XmlParser xmlParser;

    public MainController(JdbcTemplate jdbcTemplate, ContactRowMapper contactRowMapper, CustomerRowMapper customerRowMapper, XmlParser xmlParser) {
        this.jdbcTemplate = jdbcTemplate;
        this.contactRowMapper = contactRowMapper;
        this.customerRowMapper = customerRowMapper;
        this.xmlParser = xmlParser;
    }

    @GetMapping("/test")
    public List<Customer> test() {
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM CUSTOMERS WHERE ROWNUM = 1", customerRowMapper);
        return customers;
    }

    @GetMapping("/parsertest")
    public String parserTest() {
        return xmlParser.parseXml("/Users/marianbroda/Downloads/britenet-parser/src/main/resources/static/dane-osoby.xml").toString();
    }
}
