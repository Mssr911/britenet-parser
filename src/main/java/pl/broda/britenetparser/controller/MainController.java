package pl.broda.britenetparser.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @ResponseBody
    public String xmlParserTest() {
        return xmlParser.parseXml("/Users/marianbroda/Downloads/britenet-parser/src/main/resources/static/dane-osoby.xml").toString();
    }

    @GetMapping("/csvparsertest")
    @ResponseBody
    public String csvParserTest() {
        return csvParser.parseCsv("/Users/marianbroda/Downloads/britenet-parser/src/main/resources/static/dane-osoby.txt").toString();
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping(value = "/upload")
    public String uploadSimple(@RequestBody MultipartFile file) {
        return "newUsers";
    }
}
