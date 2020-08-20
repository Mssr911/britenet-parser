package pl.broda.britenetparser.service;

import org.springframework.stereotype.Component;
import pl.broda.britenetparser.model.Customer;
import pl.broda.britenetparser.model.Customers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static javax.xml.stream.XMLStreamConstants.END_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

@Component
public class XmlParser {

    JAXBContext jaxbContext;

    public Customers parseXml(String path) {


        try {
            jaxbContext = JAXBContext.newInstance(Customers.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            return (Customers) jaxbUnmarshaller.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public Customers parseXml(String path) throws FileNotFoundException, XMLStreamException, JAXBException {
//        XMLInputFactory xmlif = XMLInputFactory.newInstance();
//        XMLStreamReader streamReader = xmlif.createXMLStreamReader(new FileReader(path));
//        //set up JAXB context
//        JAXBContext jaxbContext = JAXBContext.newInstance(Customers.class);
//        Unmarshaller um = jaxbContext.createUnmarshaller();
//        // move to the root element and check its name.
//        List<Customer> customerList = new ArrayList<>();
//        streamReader.nextTag();
//        streamReader.require(START_ELEMENT, null, "persons");
//        while (streamReader.getEventType() !=  END_DOCUMENT) {
//            if(streamReader.getEventType() == START_ELEMENT && streamReader.getLocalName().equals("person")) {
//                streamReader.require(START_ELEMENT, null, "person");
//                customerList.add((Customer) um.unmarshal(streamReader));
//            } else {
//                streamReader.next();
//            }
//        }
//
//        return new Customers(customerList);
//    }





}
