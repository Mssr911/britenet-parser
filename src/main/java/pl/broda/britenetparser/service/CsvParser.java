package pl.broda.britenetparser.service;

import org.springframework.stereotype.Service;
import pl.broda.britenetparser.model.Customer;
import pl.broda.britenetparser.model.Customers;
import pl.broda.britenetparser.model.Contact;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CsvParser {


    public Customers parseCsv(String fileName) {

        File file = new File(fileName);
        List<Customer> customerList = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] person = data.split(",");
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
                        newContact.addCsvContent(new String[]{"phone", verifyPhoneNumber(tempPersonContact)});
                    } else if (tempPersonContact.contains("jbr")) {
                        newContact.addCsvContent(new String[]{"jabber", tempPersonContact});
                    } else if (tempPersonContact.contains("@") && !person[i].contains("@jabber")) {
                        newContact.addCsvContent(new String[]{"email", tempPersonContact});
                    } else newContact.addCsvContent(new String[]{"unknown", tempPersonContact});
                }
                newCustomer.setContacts(newContact);
                customerList.add(newCustomer);
            }
            return new Customers(customerList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert scanner != null;
            scanner.close();
        }
        return null;
    }

    private String verifyPhoneNumber(String number) {
        try {
            Integer.parseInt(number);
            return number;
        } catch (NumberFormatException e) {
//    TODO: LOGGER
        }
        return null;
    }
}



