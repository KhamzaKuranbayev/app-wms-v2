package webbrain.incomeexpenseapp.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webbrain.incomeexpenseapp.dto.Response;
import webbrain.incomeexpenseapp.enums.InputOutputType;
import webbrain.incomeexpenseapp.service.DashboardService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/8/2022
 * Time: 7:08 PM
 */

@RestController
@RequestMapping("/api/v1/dashboards")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    /**
     * The most common ISO Date Format yyyy-MM-dd — for example, "2000-10-31"
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/income_date_range")
    public Response getIncomeByDateRange(@RequestParam(value = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                         @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                         @RequestParam("type") InputOutputType type) {
        LocalDateTime start = startDate.atStartOfDay();                                                                                   // 2022-02-10T00:00:01
        LocalDateTime end = endDate.atStartOfDay().plus(1, ChronoUnit.DAYS).minus(1, ChronoUnit.SECONDS);       //  2022-02-11T23:59:59
        return dashboardService.getIncomeByDateRange(start, end, type);
    }



}
