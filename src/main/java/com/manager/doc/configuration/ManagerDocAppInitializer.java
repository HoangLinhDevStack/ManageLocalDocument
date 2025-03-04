package com.manager.doc.configuration;

import com.manager.doc.configuration.configdb.MgDocJDBCTemplateConfig;
import com.manager.doc.configuration.configsecurity.MgDocSecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;


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

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[]{encodingFilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setInitParameter("encoding", "UTF-8");  // Set the default encoding
    }
}
