package pl.broda.britenetparser.model;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "contacts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Contact {

    @XmlTransient
    private int id;
    @XmlTransient
    private int idCustomer;
    private List<String> email;
    private List<String> phone;
    private List<String> jabber;
    @XmlAnyElement
    private List<Element> otherContact;

    public Contact() {
    }

    public Contact(int idCustomer, List<String> email, List<String> phone, List<String> jabber, List<Element> otherContact) {
        this.idCustomer = idCustomer;
        this.email = email;
        this.phone = phone;
        this.jabber = jabber;
        this.otherContact = otherContact;
    }

    public List<String> getJabber() {
        return jabber;
    }

    public void setJabber(List<String> jabber) {
        this.jabber = jabber;
    }

    public void addJabber(String jabber) {
        this.jabber.add(jabber);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public List<String> getEmail() {
        return email;
    }

    public void addEmail(String email) {
        this.email.add(email);
    }

    public List<Element> getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(List<Element> otherContact) {
        this.otherContact = otherContact;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public void addPhone(String phone) {
        this.phone.add(phone);
    }

    @Override
    public String toString() {
        return "Contact{\n" +
                "email=" + email +
                "\n, phone=" + phone +
                "\n, jabber" + jabber +
                "\n, otherContacts=" + otherContact +
                "\n}";
    }
}
