package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.app.SignupForm;
import com.example.demo.entity.Menu;
import com.example.demo.entity.User;
import com.example.demo.repository.MenuRepository;
import com.example.demo.service.MenuService;

@Controller
public class MenuController {

	@Autowired
	MenuRepository repository;
	
	@Autowired
	MenuService menuService;
	
	/**
	 * ユーザー情報一覧画面を表示
	 * 
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@GetMapping(value = "/menu/list")
	public String menuList(Model model) {
		List<Menu> menuList = menuService.searchAll();
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
	 * メニュー新規登録画面を表示
	 * 
	 * @return メニュー新規登録完了画面
	 */
	@PostMapping(value = "/menu/signup")
	public String menuRegister(@Validated  Menu menu, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "menu/signup";
        }
		
		menuService.signup(menu);
		return "menu/registerSuccess";
	}
	
	/**
	 * メニュー情報編集画面を表示
	 * @param model Model
	 * @return ユーザー情報編集画面
	*/
	@GetMapping(value = "/menu/edit/{id}")
	public String userEdit(Model model,@PathVariable Long id) {
		// 受け取ったIDのメニュー情報を取得
		Optional<Menu> menu = repository.findById(id);
		model.addAttribute("menu", menu);
	    return "menu/edit";
	}
	
	/**
	 * メニュー情報更新処理
	 * @param model Model
	 * @return メニュー情報更新完了画面
	*/
	@PostMapping(value = "/menu/update")
	public String userUpdate(Model model, Menu menu) {
		// 受け取ったIDをもとにメニュー情報を更新
		Menu updateMenu = menuService.updateMenu(menu);
		model.addAttribute("updateMenu", updateMenu);
		return "menu/updateSuccess";
	}
	
	/**
	 * メニュー情報削除
	 * @param id 
	 * @return メニュー情報削除完了画面
	*/
	@GetMapping(value = "/menu/delete/{id}")
	public String userDelete(@PathVariable Long id) {
		// 受け取ったIDをもとにユーザー情報を削除
		menuService.deleteUser(id);
		return "menu/deleteSuccess";
	}

}