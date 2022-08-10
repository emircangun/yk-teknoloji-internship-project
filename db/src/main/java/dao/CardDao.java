package dao;

import CardEntity.Card;
import jakarta.persistence.EntityManager;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class CardDao {
    /**
     * Returning the card of the given customer no
     * @param customerNo requested customer no
     * @return Card of the given customer
     */

    // creating entity managers


    public List<Card> findByCustomerNo(String customerNo){

        String persistenceDB = "card-limit-database-jpa";
        HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
        jakarta.persistence.EntityManagerFactory emf = provider.createEntityManagerFactory(persistenceDB, Collections.emptyMap());
        EntityManager em = emf.createEntityManager();

        // get Card from the database
        List result = em.createQuery(
                "SELECT c FROM Card c WHERE c.customerNo = :customerNo",Card.class).setParameter("customerNo", customerNo).getResultList();

        // if there is no data with corresponding customer no
        if(result == null){
            throw new NoSuchElementException("No data found with the customer no: " + customerNo);
        }
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
