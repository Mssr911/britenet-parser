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
    private List<Object> content = new ArrayList<>();

    public Contact(Integer idCustomer, List<Object> content) {
        this.idCustomer = idCustomer;
        this.content = content;
    }

    public Contact() {
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
    private List<String[]> csvContent = new ArrayList<>();

    public List<String[]> getCsvContent() {
        return csvContent;
    }

    public void setCsvContent(List<String[]> csvContent) {
        this.csvContent = csvContent;
    }

    public void addCsvContent(String[] contact) {
        this.csvContent.add(contact);
    }

}
