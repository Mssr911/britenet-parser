package pl.broda.britenetparser.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.broda.britenetparser.model.Customers;

@Service
public class FileService {

    CsvParser csvParser;
    XmlParser xmlParser;

    public FileService(CsvParser csvParser, XmlParser xmlParser) {
        this.csvParser = csvParser;
        this.xmlParser = xmlParser;
    }

    public Customers parseDocument(MultipartFile file) {

        if(getFileExtension(file).equals("xml")) {
            return xmlParser.parseXml(file);
        } else if(getFileExtension(file).equals("csv")){
            return csvParser.parseCsv(file);
        }
        return null;
    }

    public String getFileExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

}
