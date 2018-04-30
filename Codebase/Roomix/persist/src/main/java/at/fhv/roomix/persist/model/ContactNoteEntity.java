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
    private String noteContent;
    private ContactEntity contact;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ContactNoteID")
    public int getContactNoteId() {
        return contactNoteId;
    }

    public void setContactNoteId(int contactNoteId) {
        this.contactNoteId = contactNoteId;
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
                Objects.equals(noteContent, that.noteContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactNoteId, noteContent);
    }

    @ManyToOne
    @JoinColumn(name = "Contact")
    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contactByContact) {
        this.contact = contactByContact;
    }
}
