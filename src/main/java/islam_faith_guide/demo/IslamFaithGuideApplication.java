package islam_faith_guide.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement

@EnableJpaRepositories(basePackages = "islam_faith_guide.demo.Repository")

public class IslamFaithGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(IslamFaithGuideApplication.class, args);
    }

}
