package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Information;
import com.example.demo.service.InformationService;

@Controller
@RequestMapping("/")
public class LoginController {
	
//	@Autowired
//    private UserDetailServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private InformationService informationService;
	
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
    public String admin(Model model) {

    	// お知らせ内容を5件に絞って取得
    	List<Information> topInformationList = informationService.searchTopInfo();
		model.addAttribute("topInformationList", topInformationList);
        return "admin";
    }
    
    /**
     * 一般ユーザーの画面に遷移する
     * @return 一般ユーザーの画面へのパス
     */
    @GetMapping("/part")
    public String user (Model model) {
    	
    	// お知らせ内容を5件に絞って取得
    	List<Information> topInformationList = informationService.searchTopInfo();
		model.addAttribute("topInformationList", topInformationList);
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