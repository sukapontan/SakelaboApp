package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Menu;
import com.example.demo.entity.User;
import com.example.demo.repository.MenuRepository;

/**
 * メニュー情報 Service
 */
@Service
public class MenuService {
	/**
	 * メニュー情報 Repository
	 */
	@Autowired
	private MenuRepository menuRepository;
	
	/**
	 * メニュー情報 全検索
	 * 
	 * @return 検索結果
	 */
	public List<Menu> searchAll() {
		return (List<Menu>) menuRepository.findAll();
	}
	
	/**
	 * メニュー情報 新規登録
	 * 
	 * @return なし
	 */
	/*public Menu signup(Menu menu) {
		return (Menu) menuRepository.signupMenu(menu);
	}*/
	public void signup(Menu menu, UserDetails userDetails) {
		
		String menu_nm = menu.getMenu_nm();
		String menu_dtl = menu.getMenu_dtl();
		int price = menu.getPrice();
		String genre = menu.getGenre();
		String note = menu.getNote();
		String ins_user = userDetails.getUsername();
		
		menuRepository.signupMenu(menu_nm, menu_dtl, price, genre, note, ins_user);
		return;
	}
	
	/**
	 * メニュー情報更新
	 * 
	 * @return メニューオブジェクト
	 */
	public Menu updateMenu(Menu menu, UserDetails userDetails) {
		
		String menu_nm = menu.getMenu_nm();
		String menu_dtl = menu.getMenu_dtl();
		int price = menu.getPrice();
		String genre = menu.getGenre();
		String note = menu.getNote();
		String upd_user = userDetails.getUsername();
		Long menu_id = menu.getMenu_id();
		
		menuRepository.updateMenu(menu_nm, menu_dtl, price, genre, note, upd_user, menu_id);
		return menu;
	}
	
	/**
	 * メニュー情報削除
	 * 
	 * @return メニューオブジェクト 
	 */
	public Optional<Menu> deleteUser(Long id) {
		Optional<Menu> menu = menuRepository.findById(id);
		menuRepository.deleteById(id);
		return menu;
	}
}
