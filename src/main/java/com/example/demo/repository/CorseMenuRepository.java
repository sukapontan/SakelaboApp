package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CorseMenu;

@Repository
public interface CorseMenuRepository extends JpaRepository<CorseMenu, Long> {
	
	// 4品コースメニュー情報取得SQLを独自に設定
	@Query(value = "select a.corse_menu_id, b.menu_nm, c.corse_nm, c.price from corses c inner join corse_menu a on c.corse_id = a.corse_id inner join menus b on a.menu_id = b.menu_id where a.corse_id = 1 order by b.genre asc", nativeQuery = true) // SQL
	List<CorseMenu> corseMenuSearchAll4();
	
	// 6品コースメニュー情報取得SQLを独自に設定
	@Query(value = "select a.corse_menu_id, b.menu_nm, c.corse_nm, c.price from corses c inner join corse_menu a on c.corse_id = a.corse_id inner join menus b on a.menu_id = b.menu_id where a.corse_id = 2 order by b.genre asc", nativeQuery = true) // SQL
	List<CorseMenu> corseMenuSearchAll6();
	
	// 8品コースメニュー情報取得SQLを独自に設定
	@Query(value = "select a.corse_menu_id, b.menu_nm, c.corse_nm, c.price from corses c inner join corse_menu a on c.corse_id = a.corse_id inner join menus b on a.menu_id = b.menu_id where a.corse_id = 3 order by b.genre asc", nativeQuery = true) // SQL
	List<CorseMenu> corseMenuSearchAll8();
}
