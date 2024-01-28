package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Menu;
import com.example.demo.form.MenuForm;
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
	public String menuSingup(MenuForm menuForm) {
		return "menu/signup";
	}
	
	/**
	 * メニュー新規登録処理
	 * 
	 * @return 
	 * 成功：メニュー一覧画面
	 * 失敗：メニュー登録画面
	 */
	@PostMapping(value = "/menu/signup")
	public String menuRegister(@Validated MenuForm menuForm, BindingResult result, Model model, MultipartFile menuImg, @AuthenticationPrincipal UserDetails userDetails) {
		
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "menu/signup";
        }
		
		// 保存先を定義
		String fileName = menuImg.getOriginalFilename();
		String uploadPath = "src/main/resources/static/images/menu/" + fileName;
		
		try {
			// アップロードファイルをバイト値に変換
			byte[] bytes = menuForm.getMenuImg().getBytes();

			// バイト値を書き込むためのファイルを作成して指定したパスに格納
			BufferedOutputStream stream = new BufferedOutputStream (new FileOutputStream(new File(uploadPath)));
			// ファイルに書き込み
			stream.write(bytes);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//　FormからEntityに情報を入れ替え
		Menu menu = new Menu();
		menu.setMenu_nm(menuForm.getMenu_nm());
		menu.setMenuImg(uploadPath.replace("src/main/resources/static", ""));
		menu.setPrice(menuForm.getPrice());
		menu.setMenu_dtl(menuForm.getMenu_dtl());
		menu.setGenre(menuForm.getGenre());
		menu.setNote(menuForm.getNote());
		
		// DBにデータを登録
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
	public String menuUpdate(Model model, MenuForm menuForm, MultipartFile menuImg, @AuthenticationPrincipal UserDetails userDetails) {
		
		// 保存先を定義
		String fileName = menuImg.getOriginalFilename();
		String uploadPath = "src/main/resources/static/images/menu/" + fileName;
		System.out.println(fileName);
		System.out.println(uploadPath);
				
		try {
			// アップロードファイルをバイト値に変換
			byte[] bytes = menuForm.getMenuImg().getBytes();

			// バイト値を書き込むためのファイルを作成して指定したパスに格納
			BufferedOutputStream stream = new BufferedOutputStream (new FileOutputStream(new File(uploadPath)));
			// ファイルに書き込み
			stream.write(bytes);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//　FormからEntityに情報を入れ替え
		Menu menu = new Menu();
		menu.setMenu_id(menuForm.getMenu_id());
		menu.setMenu_nm(menuForm.getMenu_nm());
		menu.setMenuImg(uploadPath.replace("src/main/resources/static", ""));
		menu.setPrice(menuForm.getPrice());
		menu.setMenu_dtl(menuForm.getMenu_dtl());
		menu.setGenre(menuForm.getGenre());
		menu.setNote(menuForm.getNote());
		
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