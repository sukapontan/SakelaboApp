package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Corse;
import com.example.demo.entity.CorseMenu;
import com.example.demo.repository.CorseMenuRepository;
import com.example.demo.repository.CorseRepository;

@Service
public class CorseService {
	
	/**
	 * コースRepository
	 */
	@Autowired
	private CorseRepository corseRepository;
	/**
	 * コースメニュRepository
	 */
	@Autowired
	private CorseMenuRepository corseMenuRepository;
	
	/**
	 * コース情報 全検索
	 * @return 検索結果
	 */
	public List<Corse> searchAll() {
		return (List<Corse>) corseRepository.corseSearchAll();
	}
	
	/**
	 * 4品コースメニュー情報 検索
	 * @return 検索結果
	 */
	public List<CorseMenu> searchMenuAll4() {
		return (List<CorseMenu>) corseMenuRepository.corseMenuSearchAll4();
	}
	
	/**
	 * 6品コースメニュー情報 検索
	 * @return 検索結果
	 */
	public List<CorseMenu> searchMenuAll6() {
		return (List<CorseMenu>) corseMenuRepository.corseMenuSearchAll6();
	}
	
	/**
	 * 8品コースメニュー情報 検索
	 * @return 検索結果
	 */
	public List<CorseMenu> searchMenuAll8() {
		return (List<CorseMenu>) corseMenuRepository.corseMenuSearchAll8();
	}

}
