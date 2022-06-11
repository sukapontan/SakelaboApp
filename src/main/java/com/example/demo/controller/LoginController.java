package com.example.demo.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.app.SignupForm;
import com.example.demo.service.UserDetailServiceImpl;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
    private UserDetailServiceImpl userDetailsServiceImpl;
	
	/**
     * 初期表示画面に遷移する
     * @return 初期表示画面へのパス
     */
    @GetMapping
    public String index () {
        return "index";
    }
    
    /**
     * 管理者ユーザーの画面に遷移する
     * @return 管理者ユーザーの画面へのパス
     */
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    
    /**
     * 一般ユーザーの画面に遷移する
     * @return 一般ユーザーの画面へのパス
     */
    @GetMapping("/part")
    public String user () {
        return "part";
    }

    /**
     * ログイン画面に遷移する
     * @return ログイン画面へのパス
     */
    @GetMapping("/login")
    public String login () {
    	/*
    	//ユーザーパスワードデータテーブル(user_pass)へユーザー情報を登録する。
        //その際、パスワードはBCryptで暗号化する
    	userDetailsServiceImpl.register("user"
                , passwordEncoder.encode("pass"), "ROLE_USER");
    	userDetailsServiceImpl.register("admin"
                , passwordEncoder.encode("pass"), "ROLE_ADMIN");
        */
        return "login";
    }
}