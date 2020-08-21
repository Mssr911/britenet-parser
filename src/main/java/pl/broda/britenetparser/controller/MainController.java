package pl.broda.britenetparser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.broda.britenetparser.repository.CustomerRepository;
import pl.broda.britenetparser.service.FileService;

@Controller
public class MainController {

    CustomerRepository customerRepository;
    FileService fileService;

    public MainController(CustomerRepository customerRepository, FileService fileService) {
        this.customerRepository = customerRepository;
        this.fileService = fileService;
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping(value = "/upload")
    @ResponseBody
    public String uploadFile(@RequestBody MultipartFile file) {
        customerRepository.insertCustomers(fileService.parseDocument(file));
        return "Successfully saved file: " + file.getOriginalFilename() + " to database.";
    }
}
