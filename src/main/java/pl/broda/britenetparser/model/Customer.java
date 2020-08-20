package pl.broda.britenetparser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    @XmlElement(name = "contacts")
    private Contact contacts;

    public Customer() {
        this.contacts = new Contact();
    }

    public Customer(Integer id, String name, String surname, Integer age, Contact contacts) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.contacts = contacts;
    }

    public Customer(String name, String surname, Integer age, Contact contacts) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.contacts = contacts;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Contact getContacts() {
        return contacts;
    }

    public void setContacts(Contact contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Customer{\n" +
                "id=" + id +
                "\n, name='" + name + '\'' +
                "\n, surname='" + surname + '\'' +
                "\n, age=" + age +
                "\n, contacts=" + contacts +
                "\n}";
    }
}
