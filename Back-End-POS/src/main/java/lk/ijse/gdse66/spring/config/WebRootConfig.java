package lk.ijse.gdse66.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JPAConfig.class)
@ComponentScan(basePackages = {"lk.ijse.gdse66.spring.service","lk.ijse.gdse66.spring.repo"})
public class WebRootConfig {

}
