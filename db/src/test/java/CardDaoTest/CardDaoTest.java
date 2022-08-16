package CardDaoTest;

import CardEntity.Card;
import dao.CardDao;
import jakarta.transaction.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class CardDaoTest {

    CardDao cardDao = new CardDao();
    String customerNo;
    List<Card> cardList;
    @Test
    @Transactional
    public void FindByCustomerNoTest_Success()
    {
        Card card = new Card();
        card.setCustomerNo("2486751673");
        card.setCardNo("4916924457536400");
        card.setAccountNo("753159456");
        card.setLimit(BigDecimal.valueOf(0.00));

        customerNo = "2486751673";
        cardList = cardDao.findByCustomerNo(customerNo, "0");
        Assert.assertNotNull(cardList);
        Assert.assertEquals(2, cardList.size());

        customerNo = "2486751673";
        cardList = cardDao.findByCustomerNo(customerNo, "0");
        Assert.assertNotNull(cardList);
        Assert.assertEquals("2486751673", cardList.get(0).getCustomerNo());
        cardList.clear();
    }
    @Test
    @Transactional
    public void FindByCustomerNo_invalidInput()
    {
        customerNo = "xxx";
        cardList = cardDao.findByCustomerNo(customerNo, "0");
        Assert.assertNotNull(cardList);
        Assert.assertEquals(0, cardList.size());
        cardList.clear();
    }
    @Test
    @Transactional
    public void FindByCustomerNo_NullResult()
    {
        customerNo = "12345";
        cardList = cardDao.findByCustomerNo(customerNo, "0");
        Assert.assertNotNull(cardList);
        Assert.assertEquals(0, cardList.size());
        cardList.clear();
    }
    @Test
    @Transactional
    public void FindByCustomerNo_NullInput()
    {
        customerNo = "";
        cardList = cardDao.findByCustomerNo(customerNo, "0");
        Assert.assertNotNull(cardList);
        Assert.assertEquals(0, cardList.size());
        cardList.clear();
    }
}
