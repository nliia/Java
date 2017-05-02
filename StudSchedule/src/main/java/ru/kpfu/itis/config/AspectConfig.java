package ru.kpfu.itis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Liia
 */
@EnableAspectJAutoProxy
@Configuration
@ComponentScan("ru.kpfu.itis.aspects")
public class AspectConfig {
}
