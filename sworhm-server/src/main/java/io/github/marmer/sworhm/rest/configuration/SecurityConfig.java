package io.github.marmer.sworhm.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable() // we are stateles (no session or cookies) so it should be fine
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .logout().deleteCookies("*").invalidateHttpSession(true).clearAuthentication(true)
                .logoutSuccessHandler(logoutSuccessHandler())
                .permitAll().and()
                .authorizeRequests()
                .antMatchers("/api/v1/**").permitAll()
                .antMatchers("/**").denyAll().and()
                .httpBasic().realmName("sworhm");
    }


    private LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        final String idForEncode = "bcrypt";
        final Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        encoders.put("noop", newNoopPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());

        final DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
        return delegatingPasswordEncoder;
    }

    private PasswordEncoder newNoopPasswordEncoder() {
        @SuppressWarnings("squid:CallToDeprecatedMethod") final PasswordEncoder instance = NoOpPasswordEncoder.getInstance();
        return instance;
    }
}