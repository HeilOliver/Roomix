package at.fhv.roomix.persist.dao;

import at.fhv.roomix.persist.exeption.PersistInternalException;
import at.fhv.roomix.persist.exeption.PersistSaveException;
import at.fhv.roomix.persist.model.ContactEntity;
import at.fhv.roomix.persist.model.PersonEntity;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Roomix
 * at.fhv.roomix.persist.dao
 * PersonDaoTest
 * 25/03/2018 OliverH
 * <p>
 * Enter Description here
 */
class PersonDaoTest {

    @Test
    void Save_WithoutSaveDContact() {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setForename("Oliver");
        contactEntity.setSurname("Heil");
        contactEntity.setCountry("Germany");
        contactEntity.setPhoneNumber("+4312132132132");
        contactEntity.setEmail("Some@some.com");
        contactEntity.setPlace("Dornbirn");
        contactEntity.setPostcode("8505");
        contactEntity.setStreet("SomeStreet 4");
        contactEntity.setActive((byte) 0);

        PersonEntity personEntity = new PersonEntity();
        personEntity.setIsVip((byte) 0);
        personEntity.setContactByContact(contactEntity);

        Session session = DaoSessionFactory.getSession();
        PersonDao instance = PersonDao.getInstance();

        try {
            instance.save(session, personEntity);
        } catch (PersistInternalException e) {
            fail("Exception is thrown @ " + e.getInnerException().getMessage());
        } catch (PersistSaveException e) {
            fail("Exception is thrown @ " + e.getMessage());
        }
    }
}