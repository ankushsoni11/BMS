import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@Slf4j
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.bms.manager.*"})
@EnableJpaRepositories(basePackages = {"com.bms.manager.repo"})
@EntityScan("com.bms.manager.entity.**")
@EnableKafka
public class ManagerService {
    public static void main(String[] args) {
        SpringApplication.run(ManagerService.class, args);
        log.info("Manager Service Starting..");
    }
}
