package webbrain.incomeexpenseapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import webbrain.incomeexpenseapp.properties.StorageProperties;

@EnableConfigurationProperties({
        StorageProperties.class
})
@SpringBootApplication
public class IncomeExpenseAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncomeExpenseAppApplication.class, args);
    }

}
