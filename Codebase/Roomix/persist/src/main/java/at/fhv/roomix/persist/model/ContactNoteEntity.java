package at.fhv.roomix.persist.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "ContactNote", schema = "Roomix", catalog = "")
public class ContactNoteEntity {
    private int contactNoteId;
    private int contact;
    private String noteContent;
    private ContactEntity contactByContact;

    @Id
    @Column(name = "ContactNoteID")
    public int getContactNoteId() {
        return contactNoteId;
    }

    public void setContactNoteId(int contactNoteId) {
        this.contactNoteId = contactNoteId;
    }

    @Basic
    @Column(name = "Contact", nullable = false, insertable = false)
    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    @Basic
    @Column(name = "NoteContent")
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
        ContactNoteEntity that = (ContactNoteEntity) o;
        return contactNoteId == that.contactNoteId &&
                contact == that.contact &&
                Objects.equals(noteContent, that.noteContent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(contactNoteId, contact, noteContent);
    }

    @ManyToOne
    @JoinColumn(name = "Contact", referencedColumnName = "ContactID", nullable = false)
    public ContactEntity getContactByContact() {
        return contactByContact;
    }

    public void setContactByContact(ContactEntity contactByContact) {
        this.contactByContact = contactByContact;
    }
}
