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
    Response getIncomeByDateRange(LocalDateTime start, LocalDateTime end, InputOutputType type);


}
