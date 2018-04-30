package at.fhv.roomix.persistlayer.dataaccess;

import at.fhv.roomix.persist.model.CreditCardEntity;
import at.fhv.roomix.persistlayer.dataaccess.dao.CreditCardDao;

import java.util.function.Supplier;

/**
 * Roomix
 * at.fhv.roomix.persistlayer.dataaccess
 * CreditCardFactory
 * 30/04/2018 Oliver
 * <p>
 * Enter Description here
 */
public class CreditCardFactory extends EntityFactory<CreditCardEntity, Integer> {

   private static final Object lock = new Object();
   private static CreditCardFactory instance;

       private CreditCardFactory() {
           super(CreditCardDao::new, 2);

       }

   public static CreditCardFactory getInstance() {
       if (instance != null) return instance;
       synchronized (lock) {
           if (instance == null) {
               instance = new CreditCardFactory();
           }
       }
       return instance;
   }
}
