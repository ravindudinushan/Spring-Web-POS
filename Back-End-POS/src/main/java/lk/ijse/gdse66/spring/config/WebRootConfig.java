package lk.ijse.gdse66.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(JPAConfig.class)
@ComponentScan(basePackages = "lk.ijse.gdse66.spring.service")
public class WebRootConfig {

}
