package at.fhv.roomix.domain.guest.model;

public class ContactNoteDomain {

    private int contactNoteId;
    private int contact;
    private String noteContent;


    public int getContactNoteId() {
        return contactNoteId;
    }

    public void setContactNoteId(int contactNoteId) {
        this.contactNoteId = contactNoteId;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }


}
