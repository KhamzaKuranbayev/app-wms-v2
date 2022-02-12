package webbrain.incomeexpenseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import webbrain.incomeexpenseapp.entity.InputOutput;

import java.time.LocalDateTime;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/12/2022
 * Time: 4:13 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private LocalDateTime expireDate;
    private Double amount;
    private Double price;
    private InputOutput inputOutput;


}
