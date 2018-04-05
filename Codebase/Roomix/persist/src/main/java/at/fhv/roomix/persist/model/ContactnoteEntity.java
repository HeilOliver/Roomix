package at.fhv.roomix.persist.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "contactnote", schema = "roomix")
public class ContactnoteEntity {
    private int contactNoteId;

    @Id
    @javax.persistence.Column(name = "ContactNoteID")
    public int getContactNoteId() {
        return contactNoteId;
    }

    public void setContactNoteId(int contactNoteId) {
        this.contactNoteId = contactNoteId;
    }

    private int contact;

    @Basic
    @javax.persistence.Column(name = "Contact")
    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    private String noteContent;

    @Basic
    @javax.persistence.Column(name = "NoteContent")
    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactnoteEntity that = (ContactnoteEntity) o;
        return contactNoteId == that.contactNoteId &&
                contact == that.contact &&
                Objects.equals(noteContent, that.noteContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(contactNoteId, contact, noteContent);
    }
}
