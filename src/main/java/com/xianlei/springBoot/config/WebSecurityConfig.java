package com.xianlei.springBoot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/","login").permitAll()//设置/和/login页面不拦截
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")//设置登陆页面
			.defaultSuccessUrl("/chat")//登陆成功后转向chat
			.permitAll()
			.and()
			.logout()
			.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() //在内存中分别配置两个用户sam和jack
			.withUser("sam").password("sam").roles("USER")
			.and()
			.withUser("jack").password("jack").roles("USER");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		//配置spring security的静态资源目拦截
		web.ignoring().antMatchers("/resources/static/**");
	}
	
}
