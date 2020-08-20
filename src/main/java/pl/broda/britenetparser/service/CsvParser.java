package pl.broda.britenetparser.service;

import org.springframework.util.NumberUtils;
import pl.broda.britenetparser.model.Contact;
import pl.broda.britenetparser.model.Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CsvParser {


//    public List<Customer> parse(String fileName) {
//
//        File file = new File(fileName);
//        List<Customer> customerList = new ArrayList<>();
//        try {
//            Scanner scanner = new Scanner(file);
//            while (scanner.hasNextLine()) {
//                String data = scanner.nextLine();
//                String[] person = data.split(",");
//                Customer newCustomer = new Customer();
//                Contact newContact = new Contact();
//
//                newCustomer.setName(person[0]);
//                newCustomer.setSurname(person[1]);
//                if (!person[2].isEmpty()) {
//                    newCustomer.setAge(Integer.valueOf(person[2]));
//
//                for (int i = 4; i < person.length; i++) {
//
//                    if (!person[i].contains("@") && person[i].replaceAll(" ", "").length() == 9) {
//                        try {
//                            Integer.parseInt(person[i].replaceAll(" ", ""));
//                            newContact.addPhone(person[i].replaceAll(" ", ""));
//                        } catch(NumberFormatException e) {
////                            TODO: LOGGER
//                        }
//                    } else if (!person[i].contains("@") && person[i].replaceAll(" ", "").length() == 5) {
//                        newContact.addJabber(person[i].replaceAll(" ", ""));
//                    } else if (s.contains("@")) {
//                        newContact.addEmail(person[i].replaceAll(" ", ""));
//                    } else {
//                        DatabaseConnection.insertOtherContact(s);
//                    }
//
//                }
//            }
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//
//    }

}
