package com.mironov.image.studio.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandler accessDeniedHandler;
    private final DataSource dataSource;

    public SecurityConfiguration(AccessDeniedHandler accessDeniedHandler, DataSource dataSource) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.dataSource = dataSource;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/activation/**", "/images/**", "/js/**", "/styles/**").permitAll()
                .antMatchers("/", "/signup", "/login/**").anonymous()
                .antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/login")
                .permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll()
                .and().rememberMe().key("uniqueKey").tokenValiditySeconds(2678400)
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).authoritiesByUsernameQuery(
                "SELECT user.username as username, role.role as role FROM user INNER JOIN user_role ON user.id = user_role.user_id INNER JOIN role ON user_role.role_id = role.id WHERE user.username = ?")
                .usersByUsernameQuery("select username, password, status from user where username = ?");
    }
}
