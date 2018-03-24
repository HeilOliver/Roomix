package at.fhv.roomix.persist;


import at.fhv.roomix.persist.model.ContactEntity;
import org.hibernate.*;

import javax.persistence.criteria.*;

import java.util.List;

public class Main {

    /*public static void main(final String[] args) throws Exception {
        final Session session = HibernateSessionFactory.getSession();
        System.out.println("Start");
        try {
            session.beginTransaction();

            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setCompanyName("Firma XYZ");
            contactEntity.setCountry("Germany");
            contactEntity.setPhoneNumber("+4312132132132");
            contactEntity.setEmail("Some@some.com");
            contactEntity.setPlace("Dornbirn");
            contactEntity.setPostcode("8505");
            contactEntity.setStreet("SomeStreet 4");
            contactEntity.setActive((byte) 0);
//            session.persist(contactEntity);

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ContactEntity> query = builder.createQuery(ContactEntity.class);

            Root<ContactEntity> from = query.from(ContactEntity.class);
            CriteriaQuery<ContactEntity> select = query
                    .select(from)
                    .where(builder.equal(from.get("country"),"Germany"))
                    .orderBy(builder.asc(from.get("companyName")));

            List<ContactEntity> resultList = session.createQuery(select).getResultList();

            for (ContactEntity entity : resultList) {
                System.out.println(entity);
            }

            session.getTransaction().commit();
        } finally {
            session.close();
        }
        System.out.println("Stop");
    }*/
}