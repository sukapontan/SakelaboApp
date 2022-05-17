package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void signup(Menu menu) {
		menuRepository.signupMenu(menu.getMenu_nm(), menu.getMenu_dtl(), menu.getPrice());
		//System.out.println(menu.getMenu_nm());
		//System.out.println(menu.getMenu_dtl());
		return;
	}
	
	/**
	 * メニュー情報更新
	 * 
	 * @return メニューオブジェクト
	 */
	public Menu updateMenu(Menu menu) {
		menuRepository.updateMenu(menu.getMenu_nm(), menu.getMenu_dtl(), menu.getPrice(), menu.getMenu_id());
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
