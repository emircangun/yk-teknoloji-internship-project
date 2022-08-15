package CardDaoTest;

import CardEntity.Card;
import dao.CardDao;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;


import java.util.List;

@SpringBootTest
public class CardDaoTest {

    @Test
    @Transactional
    void FindByCustomerNoTest()
    {
        CardDao cardDao = new CardDao();

//        Card card = new Card();
//        card.setCustomerNo("2486751673");
//        card.setCardNo("4916924457536400");
//        card.setAccountNo("753159456");
//        card.setLimit(BigDecimal.valueOf(0.00));

        //Successfull result
        String customerNo = "2486751673";
        List<Card> cardList = cardDao.findByCustomerNo(customerNo, "0");
        Assert.assertNotNull(cardList);
        Assert.assertEquals(2, cardList.size());

        //String input
        customerNo = "xxx";
        cardList = cardDao.findByCustomerNo(customerNo, "0");
        Assert.assertNotNull(cardList);
        Assert.assertEquals(0, cardList.size());

        //Null result
        customerNo = "12345";
        cardList = cardDao.findByCustomerNo(customerNo, "0");
        Assert.assertNotNull(cardList);
        Assert.assertEquals(0, cardList.size());

        //Null input
        customerNo = "";
        cardList = cardDao.findByCustomerNo(customerNo, "0");
        Assert.assertNotNull(cardList);
        Assert.assertEquals(0, cardList.size());
    }
}
