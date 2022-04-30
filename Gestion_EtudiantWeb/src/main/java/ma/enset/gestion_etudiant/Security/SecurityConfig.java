package ma.enset.gestion_etudiant.Security;

import ma.enset.gestion_etudiant.Security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {



       //inMemoryAuthentification
        /* String encodePWDuser1=passwordEncoder.encode("0000");
        String encodePWDadmin=passwordEncoder.encode("1111");
        auth.inMemoryAuthentication().withUser("user1").password(encodePWDuser1).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(encodePWDadmin).roles("USER", "ADMIN");*/

        //jdbcAuthentification
/*auth.jdbcAuthentication()
                .dataSource(dataSource)
        .usersByUsernameQuery("select username as principal , password as credentials , active from users where username=?")
        .authoritiesByUsernameQuery("select username as principal , role as role from users_roles where username=?")
                        .rolePrefix("ROLE_")
                                .passwordEncoder(passwordEncoder);*/

        //userdetailservice go to userdetailserviceimpl class
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/webjars/**", "/bg.jpg").permitAll();
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }
}
