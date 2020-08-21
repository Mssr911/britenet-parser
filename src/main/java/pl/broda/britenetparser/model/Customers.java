package pl.broda.britenetparser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "persons")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customers {

    public Customers(List<Customer> customerList) {
        this.customerList = customerList;
    }

    //    REQUIRED FOR JAXB
    public Customers() {
    }

    @XmlElement(name = "person")
    List<Customer> customerList;

    public List<Customer> getCustomerList() {
        return customerList;
    }

    @Override
    public String toString() {
        return "Customers{ \n" +
                "customerList= \n" + customerList +
                "\n}";
    }
}
