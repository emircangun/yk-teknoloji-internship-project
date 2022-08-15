package dao;

import CardEntity.Card;
import builder.LogMessageBuilder;
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
        // for logging purpose
        String uniqueID = currID;

        // logging before connecting to the database
        LogMessageBuilder.Log(
                LOG, customerNo, uniqueID,
                this.getClass().getSimpleName(),
                "Connecting to DB and searching for cards with customer no.",
                "start",
                Level.INFO
        );

        // create a database connection
        String persistenceDB = "card-limit-database-jpa";
        HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
        jakarta.persistence.EntityManagerFactory emf = provider.createEntityManagerFactory(persistenceDB, Collections.emptyMap());
        EntityManager em = emf.createEntityManager();

        // Get Card from the database
        List<Card> result = em.createQuery(
                "SELECT c FROM Card c WHERE c.customerNo = :customerNo",Card.class).setParameter("customerNo", customerNo).getResultList();

        // If there is no data with corresponding customer no
        if (result == null) {
            LogMessageBuilder.Log(
                    LOG, customerNo, uniqueID,
                    this.getClass().getSimpleName(),
                    "No data found with the customer no: " + customerNo,
                    "end",
                    Level.ERROR
            );

            throw new NoSuchElementException("No data found with the customer no: " + customerNo);
        }

        // logging after getting results
        LogMessageBuilder.Log(
                LOG, customerNo, uniqueID,
                this.getClass().getSimpleName(),
                "Data found with the customer no: " + customerNo,
                "end",
                Level.INFO
        );

        return result;
    }

}
