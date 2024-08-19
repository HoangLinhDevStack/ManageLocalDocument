package com.manager.doc.configuration.configdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MgDocJDBCTemplateConfig {

    @Autowired
    @Qualifier("datasource")
    private DataSource dbSourceConfig;


    @Bean
    public JdbcTemplate jdbcTemplate() {

        return new JdbcTemplate(dbSourceConfig);
    }

}
