package pl.broda.britenetparser.model;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement(name = "contacts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact {
    @XmlTransient
    private Integer id;
    @XmlTransient
    private Integer idCustomer;

    @XmlAnyElement(lax = true)
    private List<Object> content;

    public Contact(Integer idCustomer, List<Object> content) {
        this.idCustomer = idCustomer;
        this.content = content;
    }

    public Contact() {
        this.csvContent = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public List<Object> getContent() {
        return content;
    }

    public void setContent(List<Object> content) {
        this.content = content;
    }


//    ONLY FOR CSV PARSER
    private List<String[]> csvContent;

    public List<String[]> getCsvContent() {
        return csvContent;
    }

    public void setCsvContent(List<String[]> csvContent) {
        this.csvContent = csvContent;
    }

    public void addCsvContent(String[] contact) {
        this.csvContent.add(contact);
    }







    private List<String[]> transformContent(List<Object> list) {
        List<String[]> newList = new ArrayList<>();
        for (Object o : list) {
            Element element = (Element) o;
            newList.add(new String[]{element.getTagName(), element.getTextContent()});
        }
        return newList;
    }

    private List<String> writeContent(List<Object> list) {
        List<String> newList = new ArrayList<>();
        for (Object o : list) {
            Element element = (Element) o;
            newList.add(element.getTagName() + " - " + element.getTextContent() + "\n");
        }
        return newList;
    }

    private List<String> writeCsvContent(List<String[]> list) {
        List<String> newList = new ArrayList<>();
        for (String[] o : list) {
            newList.add(o[0] + " - " + o[1] + "\n");
        }
        return newList;
    }

    @Override
    public String toString() {
        return "SimpleContact{" +
                "content=" +
//                writeCsvContent(csvContent) +
                writeContent(content) +
                '}';
    }
}
