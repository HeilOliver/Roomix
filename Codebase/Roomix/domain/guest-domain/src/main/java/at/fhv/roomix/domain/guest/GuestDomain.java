package at.fhv.roomix.domain.guest;

/**
 * Roomix
 * at.fhv.roomix.domain.session
 * GuestDomain
 * 22/03/2018 Oliver
 * <p>
 * The implementation for the Guest Domain itself
 * */
class GuestDomain implements IGuestDomain {

    public static void main(String[] args) {
        IDataBase instance = PersistFactory.getInstance();

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

        try {
            instance.savePerson(personEntity);
        } catch (PersistInternalException e) {
            e.printStackTrace();
        } catch (PersistSaveException e) {


        }
    }

}
