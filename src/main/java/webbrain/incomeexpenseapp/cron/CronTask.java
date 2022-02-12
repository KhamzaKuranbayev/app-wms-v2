package webbrain.incomeexpenseapp.cron;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import webbrain.incomeexpenseapp.dto.ProductDto;
import webbrain.incomeexpenseapp.dto.Response;
import webbrain.incomeexpenseapp.service.DashboardService;

import java.time.LocalTime;
import java.util.List;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/12/2022
 * Time: 4:34 PM
 */

@Service
@RequiredArgsConstructor
public class CronTask {
    private final DashboardService dashboardService;

    @Scheduled(fixedDelay = 10_000L, initialDelay = 5000L)
    public void checkExpirationDateProducts() {
        Response response = dashboardService.getAllExpireDateSoonProducts();
        List<ProductDto> productDtos = (List<ProductDto>) response.getData();
        for (ProductDto dto : productDtos) {
            /*
                  PushNotification push = new PushNotification();
                  push.sendMessage(dto.productName);
             */
        }
    }

}
