package pl.broda.britenetparser.model;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
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
    @XmlAnyElement(lax = true)
    private List<Object> otherContact;
    @XmlTransient
    private List<String> otherContactValues;

    public Contact() {
        this.email = new ArrayList<>();
        this.phone = new ArrayList<>();
        this.jabber = new ArrayList<>();
        this.otherContact = new ArrayList<>();
        this.otherContactValues = new ArrayList<>();
    }

    public Contact(int idCustomer, List<String> email, List<String> phone, List<String> jabber, List<Object> otherContact) {
        this.idCustomer = idCustomer;
        this.email = email;
        this.phone = phone;
        this.jabber = jabber;
        this.otherContact = otherContact;
        this.otherContactValues = getOtherContactsAsStrings(otherContact);
    }

    //    JABBER
    public List<String> getJabber() {
        return jabber;
    }

    public void setJabber(List<String> jabber) {
        this.jabber = jabber;
    }

    public void addJabber(String jabber) {
        this.jabber.add(jabber);
    }

    public void addJabberList(List<String> jabber) {
        this.jabber.addAll(jabber);
    }

    //    ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //    ID_CUSTOMER
    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    //    EMAIL
    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public void addEmail(String email) {
        this.email.add(email);
    }

    public void addEmailList(List<String> emails) {
        this.email.addAll(emails);
    }

    //    PHONE
    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public void addPhone(String phoneNumber) {
        this.phone.add(phoneNumber);
    }

    public void addPhoneList(List<String> phones) {
        this.phone.addAll(phones);
    }

    //    OTHER CONTACTS
    public List<Object> getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(List<Object> otherContact) {
        this.otherContact = otherContact;
        this.otherContactValues = getOtherContactsAsStrings(otherContact);
    }

    public void addOtherContactList(List<Object> newContacts) {
        this.otherContact.addAll(newContacts);
        this.otherContactValues.addAll(getOtherContactsAsStrings(newContacts));
    }

    public void addSingleOtherContactValue(String newContact) {
        this.otherContactValues.add(newContact);

    }

    private List<String> getOtherContactsAsStrings(List<Object> contactObject) {
        List<String> contactsStringList = new ArrayList<>();
        for (Object contact : contactObject) {
            Element contactElement = (Element) contact;
            contactsStringList.add(contactElement.getTextContent());
        }
        return contactsStringList;
    }

    public List<String> getOtherContactValues() {
        return otherContactValues;
    }

    @Override
    public String toString() {
        return "Contact{\n" +
                "email=" + email +
                "\n, phone=" + phone +
                "\n, jabber=" + jabber +
                "\n, otherContacts=" + otherContactValues +
                "\n}";
    }
}
