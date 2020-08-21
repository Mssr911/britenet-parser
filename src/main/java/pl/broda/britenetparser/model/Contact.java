package pl.broda.britenetparser.model;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement(name = "contacts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact {

    public static final Map<String, Integer> CONTACT_TYPES = Map.of("unknown", 0, "email", 1, "phone", 2, "jabber", 3);

    @XmlAnyElement(lax = true)
    private final List<Object> content = new ArrayList<>();

    public Contact() {
//        REQUIRED FOR JAXB
    }

    public List<Object> getContent() {
        return content;
    }


    //    ONLY FOR CSV PARSER
    private final List<String[]> csvContent = new ArrayList<>();

    public List<String[]> getCsvContent() {
        return csvContent;
    }

    public void addCsvContent(String[] contact) {
        this.csvContent.add(contact);
    }

}
