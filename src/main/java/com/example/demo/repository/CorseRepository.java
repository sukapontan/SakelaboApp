package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Corse;
import com.example.demo.entity.CorseMenu;

@Repository
public interface CorseRepository extends JpaRepository<Corse, Long> {
	
	// コース情報取得SQLを独自に設定
	@Query(value = "select * from corses order by corse_id asc", nativeQuery = true) // SQL
	List<Corse> corseSearchAll();
	
}
