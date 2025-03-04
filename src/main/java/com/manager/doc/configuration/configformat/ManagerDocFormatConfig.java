package com.manager.doc.configuration.configformat;

import com.manager.doc.service.format.FormatTextUTF_8;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.manager.doc")
public class ManagerDocFormatConfig {

    @Bean // * Instance of class singleton convert from ISO_8859_1 to UTF_8
    public FormatTextUTF_8 format_to_UTF_8() {
        return FormatTextUTF_8.getINSTANCE();
    }

}
