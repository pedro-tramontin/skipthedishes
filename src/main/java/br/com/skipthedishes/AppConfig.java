package br.com.skipthedishes;

import br.com.skipthedishes.security.AuthKeyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
public class AppConfig extends WebMvcConfigurerAdapter {


    @Bean
    public AuthKeyInterceptor authKeyInterceptor() {
        return new AuthKeyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authKeyInterceptor());
    }
}
