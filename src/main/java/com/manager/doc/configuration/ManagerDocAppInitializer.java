package com.manager.doc.configuration;

import com.manager.doc.configuration.configdb.MgDocJDBCTemplateConfig;
import com.manager.doc.configuration.configsecurity.MgDocSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ManagerDocAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {

//        return new Class[]{MgDocSecurityConfig.class};
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class[]{MgDocSecurityConfig.class,
                ManagerDocAppConfig.class,
                MgDocJDBCTemplateConfig.class};
    }

    @Override
    protected String[] getServletMappings() {

        return new String[]{"/ManagerBook/*"};
    }
}
