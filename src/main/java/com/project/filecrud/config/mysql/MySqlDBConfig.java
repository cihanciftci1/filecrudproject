package com.project.filecrud.config.mysql;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.project.filecrud.repository",
        entityManagerFactoryRef = "mysqlDBEntityManagerFactory",
        transactionManagerRef = "mysqlDBTransactionManager"
)
public class MySqlDBConfig {
    @Primary
    @Bean(name = "mysqlDBDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mysqlDBEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlDBEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("mysqlDBDataSource") DataSource dataSource){
        return builder
                .dataSource(dataSource)
                .packages("com.project.filecrud.entity")
                .persistenceUnit("filecrud")
                .build();
    }

    @Primary
    @Bean(name = "mysqlDBTransactionManager")
    public PlatformTransactionManager mysqlDBTransactionManager(
            @Qualifier("mysqlDBEntityManagerFactory")EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
