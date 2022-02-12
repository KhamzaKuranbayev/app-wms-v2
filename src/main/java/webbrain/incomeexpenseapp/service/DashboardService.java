package webbrain.incomeexpenseapp.service;

import webbrain.incomeexpenseapp.dto.Response;
import webbrain.incomeexpenseapp.enums.InputOutputType;

import java.time.LocalDateTime;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/8/2022
 * Time: 7:10 PM
 */
public interface DashboardService {

    /**
     *   Kunlik kirim bo’lgan mahsulotlar (qiymati, umumiy summasi)
     * @param start
     * @param end
     * @param type
     * @return
     */
    Response getIncomeByDateRange(LocalDateTime start, LocalDateTime end, InputOutputType type);

    Response getTop10InputOutputByDateRange(LocalDateTime start, LocalDateTime end, InputOutputType type);

    Response getAllExpireDateSoonProducts();


}
