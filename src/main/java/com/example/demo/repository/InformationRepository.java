package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Information;
import com.example.demo.entity.Menu;

/**
 * インフォメーション Repository
 */
@Repository
public interface InformationRepository extends JpaRepository<Information, Long> {

	
	// インフォメーション新規登録のSQLを独自に設定
	@Modifying
	@Query(value = "INSERT INTO information(title, cont, ins_user) VALUES(:title, :cont, :ins_user) ", nativeQuery = true) // SQL
	@Transactional
	void registerInformation(String title, String cont, String ins_user);
	
	// インフォメーション更新のSQLを独自に設定
	@Modifying
	@Query(value = "UPDATE information SET title = :title, cont = :cont, upd_user = :upd_user WHERE information_id = :information_id", nativeQuery = true) // SQL
	@Transactional
	void updateInformation(Long information_id, String title, String cont, String upd_user);
	
	// インフォメーション一覧を返却
	@Query(value = "SELECT * FROM information ORDER BY (CASE WHEN upd_date IS NOT NULL THEN upd_date ELSE ins_date END) DESC", nativeQuery = true) // SQL
	List<Information> informationListALL();
	
	// インフォメーション一覧を返却
	@Query(value = "SELECT * FROM information ORDER BY (CASE WHEN upd_date IS NOT NULL THEN upd_date ELSE ins_date end) DESC LIMIT 5;", nativeQuery = true) // SQL
	List<Information> informationListTop();
	
}
