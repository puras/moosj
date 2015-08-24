package net.mooko.moosj.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import javax.servlet.ServletRegistration.Dynamic;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ApplicationConfig.class, BoneCPDataSourceConfig.class, CaptchaConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        ConfigurableSiteMeshFilter siteMeshFilter = new ConfigurableSiteMeshFilter();

//        DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy("shiroFilterBean");
//        shiroFilter.setTargetFilterLifecycle(true);

//        return new Filter[]{characterEncodingFilter, shiroFilter, siteMeshFilter};
        return new Filter[]{characterEncodingFilter, siteMeshFilter};
    }

    @Override
    protected void customizeRegistration(Dynamic registration) {
        //TODO: use a path value from config file
        MultipartConfigElement multipartConfig = new MultipartConfigElement(PathConfig.INSTANCE.getUploadTmpDir());
        registration.setMultipartConfig(multipartConfig);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        Servlet servlet = new StatViewServlet();
        Dynamic registration = servletContext.addServlet("DruidStat", servlet);
        registration.addMapping("/druid/*");


    }
}