package pl.broda.britenetparser.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.broda.britenetparser.model.Customers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

@Component
public class XmlParser {

    JAXBContext jaxbContext;

    public Customers parseXml(MultipartFile file) {


        try {
            jaxbContext = JAXBContext.newInstance(Customers.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File newFile = new File("src/main/resources/targetFile.tmp");
            file.transferTo(newFile);
            return (Customers) jaxbUnmarshaller.unmarshal(newFile);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
