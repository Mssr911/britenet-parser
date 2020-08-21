package pl.broda.britenetparser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.broda.britenetparser.model.Customer;
import pl.broda.britenetparser.model.Customers;
import pl.broda.britenetparser.model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvParser {

    protected final Logger log = LoggerFactory.getLogger(CsvParser.class);


    public Customers parseCsv(MultipartFile file) {

        List<Customer> customerList = new ArrayList<>();
        List<String> personList = fileToList(file);
        for (String line : personList) {
            String[] person = line.split(",");
            Customer newCustomer = new Customer();
            Contact newContact = new Contact();

            newCustomer.setName(person[0]);
            newCustomer.setSurname(person[1]);
            if (!person[2].isEmpty()) {
                newCustomer.setAge(Integer.valueOf(person[2]));
            }

            for (int i = 4; i < person.length; i++) {

                String tempPersonContact = person[i].replace(" ", "");

                if (!tempPersonContact.contains("@") && tempPersonContact.length() == 9) {
                    newContact.addCsvContent(new String[]{"2", verifyPhoneNumber(tempPersonContact)});
                } else if (tempPersonContact.contains("jbr")) {
//                    I DIDN'T KNOW HOW JABBER ID LOOKS LIKE SO I ASSUMED IT SHOULD CONTAIN JBR AS IN THE GIVEN EXAMPLE
                    newContact.addCsvContent(new String[]{"3", tempPersonContact});
                } else if (tempPersonContact.contains("@") && !person[i].contains("@jabber")) {
                    newContact.addCsvContent(new String[]{"1", tempPersonContact});
                } else newContact.addCsvContent(new String[]{"0", tempPersonContact});
            }
            newCustomer.setContacts(newContact);
            customerList.add(newCustomer);
        }
        return new Customers(customerList);
    }

    public List<String> fileToList(MultipartFile file) {
//        CONVERT FILE TO LIST OF STRINGS
        BufferedReader br;
        List<String> result = new ArrayList<>();
        try {
            String line;
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                result.add(line);
            }

        } catch (IOException e) {
            log.error("Failed attempt to read from file {}", file.getOriginalFilename());
        }

        return result;
    }

    private String verifyPhoneNumber(String number) {
        try {
            Integer.parseInt(number);
            return number;
        } catch (NumberFormatException e) {
            log.error("Given value is not a number: {}", number);
        }
        return null;
    }
}



