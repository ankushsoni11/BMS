import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.bms.ticket.*"})
@EnableFeignClients
public class TicketService {
    public static void main(String[] args) {
        SpringApplication.run(TicketService.class, args);
        log.info("Ticketing Service starting");
    }
}
