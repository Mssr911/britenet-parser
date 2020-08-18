package pl.broda.britenetparser.model;

public class Contact {

    private int id;
    private int idCustomer;
    private int type;
    private String contact;

    public Contact() {}

    public Contact(int id, int idCustomer, int type, String contact) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.type = type;
        this.contact = contact;
    }

    public Contact(int idCustomer, int type, String contact) {
        this.idCustomer = idCustomer;
        this.type = type;
        this.contact = contact;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
