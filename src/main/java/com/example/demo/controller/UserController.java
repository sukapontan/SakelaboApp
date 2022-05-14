package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	/**
	 * ユーザー情報 Service
	 */
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository repository;

	/**
	 * ユーザー情報一覧画面を表示
	 * 
	 * @param model Model
	 * @return ユーザー情報一覧画面
	 */
	@GetMapping(value = "/user/list")
	public String displayList(Model model) {
		List<User> userlist = userService.searchAll();
		model.addAttribute("userlist", userlist);
		return "user/list";
	}

	/**
	 * 新規ユーザー登録画面を表示
	 * 
	 * @param model Model
	 * @return 新規ユーザー登録画面
	 */
	@GetMapping(value = "/user/add")
	public String displayAdd(Model model) {
		return "user/add";
	}

	/**
	 * ユーザー情報詳細画面を表示
	 * 
	 * @param id    表示するユーザーID
	 * @param model Model
	 * @return ユーザー情報詳細画面
	*/
	@GetMapping("/user/{id}")
	public String displayView(@PathVariable Long id, Model model) {
		return "user/view";
	}

	/**
	 * ユーザー情報編集画面を表示
	 * @param model Model
	 * @return ユーザー情報編集画面
	*/
	@GetMapping(value = "/user/edit/{id}")
	public String userEdit(Model model,@PathVariable Long id) {
		// 受け取ったIDのユーザー情報を取得
		Optional<User> user = repository.findById(id);
		model.addAttribute("user", user);
	    return "user/edit";
	}
	
	/**
	 * ユーザー情報編集画面を表示
	 * @param model Model
	 * @return ユーザー情報編集画面
	*/
	@PostMapping(value = "/user/update")
	public String userUpdate(Model model, User user) {
		// 受け取ったIDをもとにユーザー情報を更新
		User updateUser = userService.updateUser(user);
		model.addAttribute("updateUser", updateUser);
		return "user/updateSuccess";
	}
	
	/**
	 * ユーザー情報編集画面を表示
	 * @param model Model
	 * @return ユーザー情報編集画面
	*/
	@GetMapping(value = "/user/delete/{id}")
	public String userDelete(@PathVariable Long id) {
		// 受け取ったIDをもとにユーザー情報を削除
		userService.deleteUser(id);
		return "user/deleteSuccess";
	}
}
