package com.packetsending_system_springboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.packetsending_system_springboot.service.UserServiceImpl;


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
	
	//Első

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
	        AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
     
        return authProvider;
    }


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
			.antMatchers("/" , "/css/**").permitAll()
			.antMatchers("/packettaking").permitAll()
			.antMatchers("/packettakingform").permitAll()
			.antMatchers("/signup").permitAll()
			.antMatchers("/signupform").permitAll()
			.antMatchers("/containerfilling").hasAnyAuthority("courier")
			.antMatchers("/containeremptying").hasAnyAuthority("courier")
			.antMatchers("/containeremptyingform").hasAnyAuthority("courier")
			.antMatchers("/containerfillingform").hasAnyAuthority("courier")
			.antMatchers("/activation/**").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login").permitAll()
			.and()
			.logout()
			.logoutSuccessUrl("/login?logout")
			.permitAll();
		
		//http.authenticationProvider(authenticationProvider());
		
        //http.headers().frameOptions().sameOrigin();
		return http.build();
		
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return null;
	}
	

	
		//Második
		/*@Bean
		public UserDetailsService userDetailsService() {
		    return super.userDetailsService();
		}
		

		@Autowired
		private UserDetailsService userService;
		
		@Autowired
		public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
			auth.userDetailsService(userService);
		}
		
		@Override
		protected void configure(HttpSecurity http) {
			try {
				http
				.authorizeRequests()
					.antMatchers("/" , "/css/**").permitAll()
					.antMatchers("/packettaking").permitAll()
					.antMatchers("/signup").permitAll()
					.anyRequest().authenticated()
					.and()
				.formLogin()
					.loginPage("/login").permitAll()
					.and()
					.logout()
					.permitAll();
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/

}
