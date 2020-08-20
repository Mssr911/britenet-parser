package pl.broda.britenetparser.service;

import org.springframework.stereotype.Component;
import pl.broda.britenetparser.model.Customers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

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
}
