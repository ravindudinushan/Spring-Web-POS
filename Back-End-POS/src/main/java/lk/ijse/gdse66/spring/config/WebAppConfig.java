package lk.ijse.gdse66.spring.config;

import lk.ijse.gdse66.spring.advisor.AppWideExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {AppWideExceptionHandler.class}, basePackages = "lk.ijse.gdse66.spring.controller")
public class WebAppConfig {
}
