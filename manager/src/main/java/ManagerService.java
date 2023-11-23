import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.bms.manager.*"})
public class ManagerService {
    public static void main(String[] args) {
        SpringApplication.run(ManagerService.class, args);
        log.info("Manager Service Starting..");
    }
}
