package at.fhv.roomix.controller.model;

/**
 * Roomix
 * at.fhv.roomix.controller.contact.model
 * PersonPojo
 * 08/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class PersonPojo {
    private int id;
    private String foreName;
    private String lastName;
    private boolean isVip, isArchive;

    public boolean isArchive() {
        return isArchive;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }

    private ContactPojo contact;

    public String getForeName() {
        return foreName;
    }

    public void setForeName(String foreName) {
        this.foreName = foreName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isVip() {
        return isVip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public ContactPojo getContact() {
        return contact;
    }

    public void setContact(ContactPojo contact) {
        this.contact = contact;
    }
}
