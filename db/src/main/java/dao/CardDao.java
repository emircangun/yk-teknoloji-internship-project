package dao;

import CardEntity.Card;
import CardLimitServiceImpl.LoggingMessage;
import jakarta.persistence.EntityManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.HibernatePersistenceProvider;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;



public class CardDao {
    private static final Logger LOG = LogManager.getLogger(CardDao.class.getName());
    /**
     * Returning the card of the given customer no
     * @param customerNo requested customer no
     * @return Card of the given customer
     */
    public List<Card> findByCustomerNo(String customerNo, String currID){

        String uniqueID = currID;
        //Response icerigi ogrenilip customer no log constructer icine yazilacak
        LoggingMessage loggingMessage = new LoggingMessage(customerNo, uniqueID, "Connecting to DB and searching for cards with customer no.", "CardDao", "start");
        String logMessage = loggingMessage.toString();
        LOG.log(Level.INFO, logMessage);

        String persistenceDB = "card-limit-database-jpa";
        HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
        jakarta.persistence.EntityManagerFactory emf = provider.createEntityManagerFactory(persistenceDB, Collections.emptyMap());
        EntityManager em = emf.createEntityManager();

        // Get Card from the database
        List<Card> result = em.createQuery(
                "SELECT c FROM Card c WHERE c.customerNo = :customerNo",Card.class).setParameter("customerNo", customerNo).getResultList();

        // If there is no data with corresponding customer no
        if(result == null){

            LoggingMessage loggingMessage2 = new LoggingMessage(customerNo, uniqueID, "No data found with the customer no: " + customerNo, "CardDao", "end");
            logMessage = loggingMessage2.toString();
            LOG.log(Level.ERROR, logMessage);

            throw new NoSuchElementException("No data found with the customer no: " + customerNo);
        }

        LoggingMessage loggingMessage3 = new LoggingMessage(customerNo, uniqueID, "Data found with the customer no: " + customerNo, "CardDao", "end");
        logMessage = loggingMessage3.toString();
        LOG.log(Level.INFO, logMessage);

        return result;
    }

}
