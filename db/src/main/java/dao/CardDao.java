package dao;

import CardEntity.Card;
import CardLimitServiceImpl.CardServiceImpl;
import CardLimitServiceImpl.LoggingMessage;
import jakarta.persistence.EntityManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


public class CardDao {
    /**
     * Returning the card of the given customer no
     * @param customerNo requested customer no
     * @return Card of the given customer
     */

    // creating entity managers

    private static final Logger LOG = LogManager.getLogger(CardDao.class.getName());


    public List<Card> findByCustomerNo(String customerNo){

        String uniqueID = UUID.randomUUID().toString();
        //Response icerigi ogrenilip customer no log constructer icine yazilacak
        LoggingMessage loggingMessage = new LoggingMessage(customerNo, uniqueID, "Connecting to DB and searching for cards with customer no.", "CardDao", "start");
        String logMessage = loggingMessage.toString();
        LOG.log(Level.INFO, logMessage);

        String persistenceDB = "card-limit-database-jpa";
        HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
        jakarta.persistence.EntityManagerFactory emf = provider.createEntityManagerFactory(persistenceDB, Collections.emptyMap());
        EntityManager em = emf.createEntityManager();

        // get Card from the database
        List result = em.createQuery(
                "SELECT c FROM Card c WHERE c.customerNo = :customerNo",Card.class).setParameter("customerNo", customerNo).getResultList();

        // if there is no data with corresponding customer no
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

    public List<Card> findByAccountNo(String AccountNo){

        String persistenceDB = "card-limit-database-jpa";
        HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
        jakarta.persistence.EntityManagerFactory emf = provider.createEntityManagerFactory(persistenceDB, Collections.emptyMap());
        EntityManager em = emf.createEntityManager();

        // get Card from the database
        List result = em.createQuery(
                "SELECT c FROM Card c WHERE c.accountNo = :accountNo",Card.class).setParameter("accountNo", AccountNo).getResultList();

        // if there is no data with corresponding account no
        if(result == null){
            throw new NoSuchElementException("No data found with the account no: " + AccountNo);
        }
        return result;
    }

    public List<Card> findByCardNo(String CardNo){

        String persistenceDB = "card-limit-database-jpa";
        HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
        jakarta.persistence.EntityManagerFactory emf = provider.createEntityManagerFactory(persistenceDB, Collections.emptyMap());
        EntityManager em = emf.createEntityManager();

        // get Card from the database
        List result = em.createQuery(
                "SELECT c FROM Card c WHERE c.cardNo = :cardNo",Card.class).setParameter("cardNo", CardNo).getResultList();

        // if there is no data with corresponding card no
        if(result == null){
            throw new NoSuchElementException("No data found with the card no: " + CardNo);
        }
        return result;
    }
    public List<Card> getAllCardLimits() {

        String persistenceDB = "card-limit-database-jpa";
        HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
        jakarta.persistence.EntityManagerFactory emf = provider.createEntityManagerFactory(persistenceDB, Collections.emptyMap());
        EntityManager em = emf.createEntityManager();

        return em.createQuery("SELECT c FROM Card c", Card.class).getResultList();
    }

}
