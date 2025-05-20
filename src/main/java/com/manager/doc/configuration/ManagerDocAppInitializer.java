package com.manager.doc.configuration;

import com.manager.doc.configuration.configdb.MgDocJDBCTemplateConfig;
import com.manager.doc.configuration.configsecurity.MgDocSecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
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
        // Configure multipart file upload settings (3.5GB max upload size)
        registration.setMultipartConfig(
                new MultipartConfigElement(
                        null,                            // Temporary directory for file storage (can be null)
                        3750L * 1024 * 1024,            // Max upload size (3.5GB)
                        3750L * 1024 * 1024,            // Max upload size per file
                        0                                // Max request size (0 means no limit)
                )
        );
    }

}
