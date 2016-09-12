package com.webservice.ticketingservice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(basePackages = "com.webservice.ticketingservice")
public class TicketingServiceConfiguration {

}
