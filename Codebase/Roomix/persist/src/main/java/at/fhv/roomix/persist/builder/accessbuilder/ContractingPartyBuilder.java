package at.fhv.roomix.persist.builder.accessbuilder;

import at.fhv.roomix.domain.guest.contact.Contact;
import at.fhv.roomix.domain.guest.contractingparty.Company;
import at.fhv.roomix.domain.guest.contractingparty.ContractingParty;
import at.fhv.roomix.domain.guest.contractingparty.Individual;
import at.fhv.roomix.domain.guest.contractingparty.TravelAgency;
import at.fhv.roomix.persist.dataaccess.factory.ContactFactory;
import at.fhv.roomix.persist.dataaccess.factory.ContractingPartyFactory;
import at.fhv.roomix.persist.exception.BuilderLoadException;
import at.fhv.roomix.persist.exception.BuilderUpdateException;
import at.fhv.roomix.persist.exception.PersistLoadException;
import at.fhv.roomix.persist.models.ContactEntity;
import at.fhv.roomix.persist.models.ContractingPartyEntity;
import org.modelmapper.ModelMapper;

import static at.fhv.roomix.persist.models.ContractingPartyEntity.ContractingPartyType.*;

/**
 * Roomix
 * at.fhv.roomix.persist.builder
 * ContractingPartyBuilder
 * 01/05/2018 Oliver
 * <p>
 * Enter Description here
 */
public class ContractingPartyBuilder {
    private static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
    }

    public static Individual getIndividual(int contactId) throws BuilderLoadException {
        if (contactId == 0) throw new BuilderLoadException("Cant load individual without contact");

        try {
            Contact contact = ContactBuilder.getContact(contactId);
            ContactEntity contactEntity = ContactFactory.getInstance().get(contactId);

            Individual individual = new Individual(contact);
            if (contactEntity.getContractingParty() != null) {
                mapper.map(contactEntity.getContractingParty(), individual);
            }
            return individual;
        } catch (PersistLoadException e) {
            throw new BuilderLoadException("Cant load contact, see inner exception fore more detail", e);
        }
    }

    public static void updateIndividual(Individual individual) throws BuilderUpdateException {
        if (individual == null) throw new IllegalArgumentException();
        if (individual.getContact() == null) throw new IllegalArgumentException();

        try {
            ContractingPartyEntity entity = individual.getId() == 0 ? new ContractingPartyEntity(INDIVIDUAL) :
                    ContractingPartyFactory.getInstance().get(individual.getId());

            ContactEntity contactEntity = ContactBuilder.updateEntity(individual.getContact());
            entity.setContact(contactEntity);

            ContractingPartyFactory.getInstance().saveOrUpdate(entity);
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException("Cant update INDIVIDUAL, see inner exception fore more detail", e);
        }
    }

    public static Company getCompany(int contactId) throws BuilderLoadException {
        if (contactId == 0) throw new BuilderLoadException("Cant load individual without contact");

            Contact contact = ContactBuilder.getContact(contactId);
            return new Company(contact);

    }

    public static void updateCompany(Company company) throws BuilderUpdateException {
        if (company == null) throw new IllegalArgumentException();
        if (company.getContact() == null) throw new IllegalArgumentException();

        try {
            ContractingPartyEntity entity = company.getId() == 0 ? new ContractingPartyEntity(COMPANY) :
                    ContractingPartyFactory.getInstance().get(company.getId());

            ContactEntity contactEntity = ContactBuilder.updateEntity(company.getContact());
            entity.setContact(contactEntity);

            ContractingPartyFactory.getInstance().saveOrUpdate(entity);
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException("Cant update COMPANY, see inner exception fore more detail", e);
        }
    }

    public static TravelAgency getTravelAgency(int contactId) throws BuilderLoadException {
        if (contactId == 0) throw new BuilderLoadException("Cant load individual without contact");

        try {
            Contact contact = ContactBuilder.getContact(contactId);
            ContactEntity contactEntity = ContactFactory.getInstance().get(contactId);

            TravelAgency individual = new TravelAgency(contact);
            if (contactEntity.getContractingParty() != null) {
                mapper.map(contactEntity.getContractingParty(), individual);
            }


            return individual;
        } catch (PersistLoadException e) {
            throw new BuilderLoadException("Cant load contact, see inner exception fore more detail", e);
        }
    }

    public static void updateTravelAgency(TravelAgency agency) throws BuilderUpdateException {
        if (agency == null) throw new IllegalArgumentException();
        if (agency.getContact() == null) throw new IllegalArgumentException();

        try {
            ContractingPartyEntity entity = agency.getId() == 0 ? new ContractingPartyEntity(TRAVEL_AGENCY) :
                    ContractingPartyFactory.getInstance().get(agency.getId());

            ContactEntity contactEntity = ContactBuilder.updateEntity(agency.getContact());
            entity.setContact(contactEntity);

            ContractingPartyFactory.getInstance().saveOrUpdate(entity);
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException("Cant update TRAVEL_AGENCY, see inner exception fore more detail", e);
        }
    }

    static ContractingPartyEntity updateParty(ContractingParty party) throws BuilderUpdateException {
        if (party == null) throw new IllegalArgumentException();
        if (party.getContact() == null) throw new IllegalArgumentException();

        try {
            // TODO Here we need to set the correct Type and everything else.
            // May this is an agency and the object hold much more..
            ContractingPartyEntity entity = party.getId() == 0 ? new ContractingPartyEntity(INDIVIDUAL) :
                    ContractingPartyFactory.getInstance().get(party.getId());

            ContactEntity contactEntity = ContactBuilder.updateEntity(party.getContact());
            entity.setContact(contactEntity);

            ContractingPartyFactory.getInstance().saveOrUpdate(entity);
            return entity;
        } catch (PersistLoadException e) {
            throw new BuilderUpdateException("Cant update ContractingParty, see inner exception fore more detail", e);
        }
    }

    public static ContractingParty getByContact(int contactId)throws BuilderLoadException {
        ContactEntity entity;
        try {
            entity = ContactFactory.getInstance().get(contactId);
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e.getMessage(), e);
        }
        ContractingPartyEntity contractingParty = entity.getContractingParty();
        if (contractingParty == null) return null;
        return get(entity.getContractingParty().getContractingPartyId());
    }

    public static ContractingParty get(int id) throws BuilderLoadException {
        ContractingPartyEntity entity = null;
        try {
            entity = ContractingPartyFactory.getInstance().get(id);
        } catch (PersistLoadException e) {
            throw new BuilderLoadException(e);
        }
        switch (entity.getContractingPartyType()) {
            case "INDIVIDUAL":
                return getIndividual(id);

            case "COMPANY":
                return getCompany(id);

            case "TRAVEL_AGENCY":
                return getTravelAgency(id);

            default:
                throw new BuilderLoadException(String.format("Cant find contractingParty type: %s", entity.getContractingPartyType()));
        }
    }
}
