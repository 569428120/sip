package com.sip.config;

import com.bstek.ureport.console.UReportServlet;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableAutoConfiguration
@Configuration
public class ReportConfig {
    @Bean
    public ServletRegistrationBean<UReportServlet> ureportServlet() {
        return new ServletRegistrationBean<>(new UReportServlet(), "/ureport/*");
    }
}
