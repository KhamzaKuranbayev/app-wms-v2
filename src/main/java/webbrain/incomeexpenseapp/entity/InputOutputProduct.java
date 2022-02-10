package webbrain.incomeexpenseapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputOutputProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private InputOutput inputOutput;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDateTime expireDate;

    public InputOutputProduct(Product product, InputOutput inputOutput, Double amount, Double price, LocalDateTime expireDate) {
        this.product = product;
        this.inputOutput = inputOutput;
        this.amount = amount;
        this.price = price;
        this.expireDate = expireDate;
    }

    /*
            inputOutput                 product                     amount         price         expireDate
                1                           5                       50              $60            20/02/2022
                1                           8
                1                           12
                1                           15
                1                           50

     */

}
