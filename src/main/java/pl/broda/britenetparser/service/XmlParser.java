package pl.broda.britenetparser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    protected final Logger log = LoggerFactory.getLogger(XmlParser.class);

    JAXBContext jaxbContext;

    public Customers parseXml(MultipartFile file) {

        try {
            jaxbContext = JAXBContext.newInstance(Customers.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            JAXB DOES NOT SUPPORT MultipartFile
            File newFile = File.createTempFile("tempFile", "xml");
            file.transferTo(newFile);
            return (Customers) jaxbUnmarshaller.unmarshal(newFile);
        } catch (JAXBException e) {
            log.error("Failed attempt to unmarshall file: {}", file.getOriginalFilename());
        } catch (IOException e) {
            log.error("Failed attempt to read from file: {}", file.getOriginalFilename());
        }
        return null;
    }
}
