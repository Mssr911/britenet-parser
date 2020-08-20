package pl.broda.britenetparser.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Element;
import pl.broda.britenetparser.model.Contact;
import pl.broda.britenetparser.model.Customer;
import pl.broda.britenetparser.service.CsvParser;
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
    CsvParser csvParser;

    public MainController(JdbcTemplate jdbcTemplate, ContactRowMapper contactRowMapper, CustomerRowMapper customerRowMapper, XmlParser xmlParser, CsvParser csvParser) {
        this.jdbcTemplate = jdbcTemplate;
        this.contactRowMapper = contactRowMapper;
        this.customerRowMapper = customerRowMapper;
        this.xmlParser = xmlParser;
        this.csvParser = csvParser;
    }

    @GetMapping("/test")
    public List<Customer> test() {
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM CUSTOMERS WHERE ROWNUM = 1", customerRowMapper);
        return customers;
    }

    @GetMapping("/xmlparsertest")
    public String xmlParserTest() {
        return xmlParser.parseXml("/Users/marianbroda/Downloads/britenet-parser/src/main/resources/static/dane-osoby.xml").toString();
    }

    @GetMapping("/csvparsertest")
    public String csvParserTest() {
        return csvParser.parseCsv("/Users/marianbroda/Downloads/britenet-parser/src/main/resources/static/dane-osoby.txt").toString();
    }

    @GetMapping("/calyhuj")
    public String calyhuj() {
        Customer customer = new Customer();
        Contact huj = new Contact();
        huj.addSingleOtherContactValue("pizdeczka");
        huj.addEmail("psitka@dupka.com");
        customer.setContacts(huj);
        return customer.toString();
    }
}
