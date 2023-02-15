package com.example.demo.repository;

import java.util.List;

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
	
	// メニューの一覧取得のSQLを独自に設定
	@Query(value = "SELECT * FROM menus ORDER BY CASE WHEN upd_date IS NOT NULL THEN upd_date ELSE ins_date END DESC;", nativeQuery = true)
	List<Menu> findAll();
	
	// メニュー新規登録のSQLを独自に設定
	@Modifying
	@Query(value = "INSERT INTO menus(menu_nm, menu_dtl, price, genre, note, ins_user) VALUES(:menu_nm, :menu_dtl, :price, :genre, :note, :ins_user) ", nativeQuery = true)
	@Transactional
	void signupMenu(String menu_nm, String menu_dtl, int price, String genre, String note, String ins_user);
	
	// メニュー更新のSQLを独自に設定
	@Modifying
	@Query(value = "UPDATE menus SET menu_nm = :menu_nm, menu_dtl = :menu_dtl, price = :price, genre = :genre, note = :note, upd_user = :upd_user WHERE menu_id = :menu_id", nativeQuery = true)
	@Transactional
	void updateMenu(String menu_nm, String menu_dtl, int price, String genre, String note, String upd_user, Long menu_id);
	
	// アルバイト用のメニューリスト一覧を返却
	// @Query(value = "SELECT a.* FROM menus a INNER JOIN corse_menu b ON a.menu_id = b.menu_id WHERE b.corse_id = 3 ORDER BY genre", nativeQuery = true)
	@Query(value = "SELECT * FROM menus ORDER BY genre", nativeQuery = true)
	List<Menu> partSelectMenu();
	
}