package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.AuthenticationSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Lazy  // エラーが出るのでLazyを使用したが非推奨？
    private UserDetailsService userDetailsService;
	
	// 認証成功時の処理をカスタマイズ
	@Autowired
	private AuthenticationSuccessHandler AuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // コンソールのログに警告が出るので一旦コメントアウト
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()  // 認可の設定
        	.antMatchers("/login", "/error", "/login-error", "/passwordReset", "/passwordResetSeccess").permitAll()
        	.antMatchers("/admin", "/admin/**").hasAuthority("ROLE_ADMIN") 
            .anyRequest()
            .authenticated();  // それ以外は全て認証無しの場合アクセス不許可
        http.formLogin() // ログイン時の設定
        	.loginPage("/login")
        	.successHandler(AuthenticationSuccessHandler)
            .permitAll();
        http.logout()  // ログアウト設定
            .permitAll();
    }
}