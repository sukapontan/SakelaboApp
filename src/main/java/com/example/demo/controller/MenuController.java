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

import com.example.demo.entity.Menu;
import com.example.demo.repository.MenuRepository;
import com.example.demo.service.MenuService;

@Controller
public class MenuController {

	@Autowired
	MenuService menuService;
	
	@Autowired
	MenuRepository repository;
	
	/**
	 * メニュー情報一覧画面を表示(社員用)
	 * 
	 * @param model Model
	 * @return メニュー一覧画面
	 */
	@GetMapping(value = "/menu/list")
	public String menuList(Model model) {
		List<Menu> menuList = menuService.searchAll();
		model.addAttribute("menulist", menuList);
		return "menu/list";
	}
	
	/**
	 * メニュー情報一覧画面を表示(アルバイト用)
	 * 
	 * @param model Model
	 * @return メニュー一覧画面
	 */
	@GetMapping(value = "/part/menu/list")
	public String partMenuList(Model model) {
		List<Menu> menuList = menuService.partSearchAll();
		model.addAttribute("menulist", menuList);
		return "menu/list";
	}
	
	/**
	 * メニュー新規登録画面を表示
	 * 
	 * @return メニュー新規登録画面
	 */
	@GetMapping(value = "/menu/signup")
	public String menuSingup(Menu menu) {
		return "menu/signup";
	}
	
	/**
	 * メニュー新規登録処理
	 * 
	 * @return メニュー一覧画面
	 */
	@PostMapping(value = "/menu/signup")
	public String menuRegister(@Validated  Menu menu, BindingResult result, Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "menu/signup";
        }
		menuService.signup(menu, userDetails);
//		return "menu/registerSuccess";
		return "redirect:/menu/list";
	}
	
	/**
	 * メニュー情報編集画面を表示
	 * @param model Model
	 * @return メニュー編集画面
	*/
	@GetMapping(value = "/menu/edit/{id}")
	public String menuEdit(Model model,@PathVariable Long id) {
		// 受け取ったIDのメニュー情報を取得
		Optional<Menu> menu = repository.findById(id);
		model.addAttribute("menu", menu);
	    return "menu/edit";
	}
	
	/**
	 * メニュー詳細画面を表示
	 * @param model Model
	 * @return メニュー詳細画面
	*/
	@GetMapping(value = "/menu/detail/{id}")
	public String menuDetail(Model model,@PathVariable Long id) {
		// 受け取ったIDのメニュー詳細情報を取得
		Optional<Menu> menu = repository.findById(id);
		model.addAttribute("menu", menu.get());

		return "menu/detail";
	}	
	
	/**
	 * メニュー情報更新処理
	 * @param model Model
	 * @return メニュー一覧画面
	*/
	@PostMapping(value = "/menu/update")
	public String menuUpdate(Model model, Menu menu, @AuthenticationPrincipal UserDetails userDetails) {
		
		// 受け取ったIDをもとにメニュー情報を更新
		Menu updateMenu = menuService.updateMenu(menu, userDetails);
		model.addAttribute("updateMenu", updateMenu);

		return "redirect:/menu/list";
	}
	
	/**
	 * メニュー情報削除
	 * @param id 
	 * @return メニュー一覧画面
	*/
	@GetMapping(value = "/menu/delete/{id}")
	public String menuDelete(@PathVariable Long id) {
		
		// 受け取ったIDをもとにユーザー情報を削除
		menuService.deleteUser(id);
		
		return "redirect:/menu/list";
	}

}