package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Corse;
import com.example.demo.entity.CorseMenu;
import com.example.demo.service.CorseService;

@Controller
public class CorseController {
	
	@Autowired
	private CorseService corseService;

	/**
	 * メニュー情報一覧画面を表示
	 * 
	 * @param model Model
	 * @return コース一覧画面
	 */
	@GetMapping(value = "/corse/list")
	public String corseList(Model model) {
		
		// コース名とコースの価格を取得
		List<Corse> corseList = corseService.searchAll();
		model.addAttribute("corseList", corseList);
		
		// 4品コースのメニューを取得
		List<CorseMenu> corseMenuList4 = corseService.searchMenuAll4();
		model.addAttribute("corseMenuList4", corseMenuList4);
		
		// 6品コースのメニューを取得
		List<CorseMenu> corseMenuList6 = corseService.searchMenuAll6();
		model.addAttribute("corseMenuList6", corseMenuList6);
		
		// 8品コースのメニューを取得
		List<CorseMenu> corseMenuList8 = corseService.searchMenuAll8();
		model.addAttribute("corseMenuList8", corseMenuList8);
		
		return "corse/list";
	}
	
}
