package com.manager.doc.configuration.configdb;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.manager.doc")
public class MgDocDBSourceConfig {

    @Bean // set up connecting db
    public DataSource datasource() {
        String nameDB = "db_mg_doc(binh)";
        String url = "jdbc:mysql://127.0.0.1:3306/" + nameDB + "?useSSL=false";
        String userName = "root";
        String passWord = "123456";

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(userName);
        driverManagerDataSource.setPassword(passWord);
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return driverManagerDataSource;
    }


}
