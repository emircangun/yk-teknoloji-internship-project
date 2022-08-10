package dao;

import CardEntity.Card;
import jakarta.persistence.EntityManager;
import org.hibernate.jpa.HibernatePersistenceProvider;
import java.util.Collections;
import java.util.NoSuchElementException;

public class CardDao {

    /**
     * Returning the card of the given customer no
     * @param customerNo requested customer no
     * @return Card of the given customer
     */
    public Card findByCustomerNo(String customerNo){
        // creating entity managers
        HibernatePersistenceProvider provider = new HibernatePersistenceProvider();
        jakarta.persistence.EntityManagerFactory emf = provider.createEntityManagerFactory("card-limit-database-jpa", Collections.emptyMap());
        EntityManager em = emf.createEntityManager();

        // get Card from the database
        Card result = em.find(Card.class, customerNo);

        // if there is no data with corresponding customer no
        if(result == null){
            throw new NoSuchElementException("No data found with the customer no: " + customerNo);
        }

        return result;
    }

}
