package lk.ijse.gdse66.spring.config;

import jakarta.persistence.EntityManagerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "lk.ijse.gdse66.spring.repo")
@Configuration
@PropertySource("classpath:application.properties")
public class JPAConfig {

    Environment env;

    public JPAConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setUrl(env.getRequiredProperty("spring.datasource.url"));
        dmds.setUsername(env.getRequiredProperty("spring.datasource.username"));
        dmds.setPassword(env.getRequiredProperty("spring.datasource.password"));
        dmds.setDriverClassName(env.getRequiredProperty("spring.datasource.driverClassName"));
        return dmds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setDatabasePlatform(env.getRequiredProperty("spring.jpa.hibernate.dialect"));

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("lk.ijse.gdse66.spring.entity");
        factory.setDataSource(dataSource);
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}