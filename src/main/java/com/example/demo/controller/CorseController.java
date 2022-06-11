package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Menu;

@Controller
public class CorseController {

	/**
	 * メニュー情報一覧画面を表示
	 * 
	 * @param model Model
	 * @return コース一覧画面
	 */
	@GetMapping(value = "/corse/list")
	public String corseList(Model model) {
		return "corse/list";
	}
	
}
