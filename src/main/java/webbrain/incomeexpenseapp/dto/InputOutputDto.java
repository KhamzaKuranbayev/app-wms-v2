package webbrain.incomeexpenseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import webbrain.incomeexpenseapp.entity.*;
import webbrain.incomeexpenseapp.enums.InputOutputType;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/8/2022
 * Time: 6:29 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputOutputDto {
    private LocalDateTime date;
    private Long warehouseId;
    private Long supplierId;
    private Long currencyId;
    private String factureNumber;
    private InputOutputType type;

    // INNER CLASS
    @Data
    public static class DetailDto {
        private Long productId;
        private Double amount;
        private Double price;
        private LocalDateTime expireDate;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetDto {
        private LocalDateTime dateTime;
        private Werehouse werehouse;
        private User supplier;
        private Currency currency;
        private UUID code;
        private InputOutputType type;
        private List<InputOutputProduct> details;
    }

}
