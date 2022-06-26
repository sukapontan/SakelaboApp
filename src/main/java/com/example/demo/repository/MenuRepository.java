package com.example.demo.repository;

import java.util.Iterator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Menu;

/**
 * 料理メニュー情報 Repository
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	// メニュー新規登録のSQLを独自に設定
	@Modifying
	@Query(value = "INSERT INTO menus(menu_nm, menu_dtl, price, genre, note, ins_user) VALUES(:menu_nm, :menu_dtl, :price, :genre, :note, :ins_user) ", nativeQuery = true) // SQL
	@Transactional
	void signupMenu(String menu_nm, String menu_dtl, int price, String genre, String note, String ins_user);
	
	// メニュー更新のSQLを独自に設定
	@Modifying
	@Query(value = "UPDATE menus SET menu_nm = :menu_nm, menu_dtl = :menu_dtl, price = :price, genre = :genre, note = :note, upd_user = :upd_user WHERE menu_id = :menu_id", nativeQuery = true) // SQL
	@Transactional
	void updateMenu(String menu_nm, String menu_dtl, int price, String genre, String note, String upd_user, Long menu_id);
}