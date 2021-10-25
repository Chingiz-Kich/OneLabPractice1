package com.example.onelabpractice1.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.example.onelabpractice1")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfiguration {

}
