package CardService;


import CardEntity.Card;
import CardRepository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardRepository repository;

    public List<Card> getCardLimits()
    {
        return repository.findAll();
    }

    public Card getCardLimitByCardNo(BigDecimal cardNo)
    {
        return repository.findByCardNo(cardNo);
    }

    public Card getCardLimitByAccountNo(BigDecimal accountNo)
    {
        return repository.findByAccountNo(accountNo);
    }

    public Card getCardLimitByCustomerNo(BigDecimal customerNo)
    {
        return repository.findById(customerNo).orElse(null);
    }
}
