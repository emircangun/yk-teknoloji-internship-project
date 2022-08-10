package CardEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Card")
@Table(name="card_limits")
public class Card
{
    @Id
    @GeneratedValue
    @Column(name="card_no")
    private BigDecimal cardNo;
    @Column(name="customer_no")
    private BigDecimal customerNo;
    @Column(name="account_no")
    private BigDecimal accountNo;
    @Column(name="limit")
    private BigDecimal limit;
}
