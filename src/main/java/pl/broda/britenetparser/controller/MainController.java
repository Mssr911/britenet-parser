package pl.broda.britenetparser.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.broda.britenetparser.model.Customers;
import pl.broda.britenetparser.repository.CustomerRepository;
import pl.broda.britenetparser.model.Customer;
import pl.broda.britenetparser.service.CsvParser;
import pl.broda.britenetparser.service.FileService;
import pl.broda.britenetparser.service.XmlParser;
import pl.broda.britenetparser.utils.ContactRowMapper;
import pl.broda.britenetparser.utils.CustomerRowMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    ContactRowMapper contactRowMapper;
    CustomerRowMapper customerRowMapper;
    XmlParser xmlParser;
    CsvParser csvParser;
    CustomerRepository customerRepository;
    FileService fileService;

    public MainController(ContactRowMapper contactRowMapper, CustomerRowMapper customerRowMapper, XmlParser xmlParser, CsvParser csvParser, CustomerRepository customerRepository, FileService fileService) {
        this.contactRowMapper = contactRowMapper;
        this.customerRowMapper = customerRowMapper;
        this.xmlParser = xmlParser;
        this.csvParser = csvParser;
        this.customerRepository = customerRepository;
        this.fileService = fileService;
    }

    @PostMapping("/test")
    public void insert() {
        Customer customer1 = new Customer();
        customer1.setName("Rysiek");
        customer1.setSurname("ZKlanu");
        customer1.setAge(45);
        Customer customer2 = new Customer();
        customer2.setName("Krzysiek");
        customer2.setSurname("ZZadupia");
        customer2.setAge(62);
        List<Customer> list = new ArrayList<>(List.of(customer1, customer2));

        customerRepository.insertCustomerList(list);
    }


    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public String uploadFile(@RequestBody MultipartFile file) {
        customerRepository.insertCustomers(fileService.parseDocument(file));
        return fileService.getFileExtension(file);
    }

    @PostMapping("/testcsv")
    @ResponseBody
    public List<String[]> testcsv(MultipartFile file) {
        return csvParser.parseCsv(file).getCustomerList().get(0).getContacts().getCsvContent();
    }

    @GetMapping("/test")
    @ResponseBody
    public int test() {
        Customer customer = new Customer();
        customer.setName("Dupa");
        customer.setSurname("Psia");
        customer.setAge(14);
        return customerRepository.insertCustomer(customer);
    }
}
