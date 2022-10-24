package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Information;
import com.example.demo.entity.Menu;
import com.example.demo.repository.InformationRepository;
import com.example.demo.service.InformationService;

@Controller
public class InformationController {
	
	@Autowired
	InformationService informationService;
	
	@Autowired
	InformationRepository informationRepository;

	/**
	 * インフォメーション一覧画面に遷移
	 * 
	 * @return インフォメーション一覧画面
	 */
	@GetMapping(value = "/information/list")
	public String informationList(Model model) {
		List<Information> informationList = informationService.searchAll();
		model.addAttribute("informationlist", informationList);
		
		return "information/list";
	}
	
	/**
	 * インフォメーション新規登録画面に遷移
	 * 
	 * @return インフォメーション新規登録画面
	 */
	@GetMapping(value = "/information/register")
	public String informationIndex(Model model) {
		return "information/register";
	}

	/**
	 * インフォメーション新規登録処理
	 * 
	 * @return エラーの場合 ：インフォメーション新規登録画面
	 * @return 登録成功の場合：インフォメーション一覧画面
	 */
	@PostMapping(value = "/information/register")
	public String informationRegister(@Validated Information information, BindingResult result, Model model, @AuthenticationPrincipal UserDetails userDetails) {

		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "information/register";
		}
		
		informationService.register(information, userDetails);
		return "redirect:/information/list";
	}
	
	/**
	 * インフォメーション詳細画面を表示
	 * @param model Model
	 * @return インフォメーション詳細画面
	*/
	@GetMapping(value = "/information/detail/{id}")
	public String menuDetail(Model model,@PathVariable Long id) {
		// 受け取ったIDのインフォメーション詳細情報を取得
		Optional<Information> information = informationRepository.findById(id);
		model.addAttribute("information", information.get());
		return "information/detail";
	}
	
	/**
	 * インフォメーション編集画面を表示
	 * @param model Model
	 * @return インフォメーション一覧画面
	*/
	@GetMapping(value = "/information/edit/{id}")
	public String menuEdit(Model model,@PathVariable Long id) {
		// 受け取ったIDのメニュー情報を取得
		Optional<Information> information = informationRepository.findById(id);
		model.addAttribute("information", information);
	    return "information/edit";
	}
	
	/**
	 * インフォメーション更新
	 * @param model Model
	 * @return インフォメーション一覧画面
	*/
	@PostMapping(value = "/information/update")
	public String menuUpdate(@Validated Information information, BindingResult result, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "information/edit";
		}
		
		// 受け取ったIDをもとにメニュー情報を更新
		Information updateInformation = informationService.updateInformation(information, userDetails);
		model.addAttribute("updateInformation", updateInformation);
		return "redirect:/information/list";
	}
	
	/**
	 * インフォメーション削除
	 * @param id
	 * @return インフォメーション一覧画面
	*/
	@GetMapping(value = "/information/delete/{id}")
	public String informationDelete(@PathVariable Long id) {
		// 受け取ったIDをもとにユーザー情報を削除
		informationService.deleteInformation(id);
		return "redirect:/information/list";
	}
}
