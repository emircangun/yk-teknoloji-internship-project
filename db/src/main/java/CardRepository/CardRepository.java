package CardRepository;


import CardEntity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface CardRepository extends JpaRepository<Card,BigDecimal>
{

    Card findByAccountNo(BigDecimal accountNo);
    Card findByCardNo(BigDecimal cardNo);

}
