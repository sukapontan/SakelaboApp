package com.example.demo.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * コースメニュー情報 Entity
 */
@Entity
@Data
public class CorseMenu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * コースメニューID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long corse_menu_id;
	
	private String menu_nm;

	private String corse_nm;
	
	private int price;
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Long getCorse_menu_id() {
		return corse_menu_id;
	}

	public String getCorse_nm() {
		return corse_nm;
	}

	public void setCorse_nm(String corse_nm) {
		this.corse_nm = corse_nm;
	}

	public void setCorse_menu_id(Long corse_menu_id) {
		this.corse_menu_id = corse_menu_id;
	}

	public String getMenu_nm() {
		return menu_nm;
	}

	public void setMenu_nm(String menu_nm) {
		this.menu_nm = menu_nm;
	}
	
//	/**
//	 * コースID
//	 */
//	@Column(name = "corse_id")
//	private Long corse_id;
//	
//	/**
//	 * メニューID
//	 * 
//	 */
//	@Column(name = "menu_id")
//	private Long menu_id;
//	
//	public Long getCorse_id() {
//		return corse_id;
//	}
//
//	public void setCorse_id(Long corse_id) {
//		this.corse_id = corse_id;
//	}
//
//	public Long getMenu_id() {
//		return menu_id;
//	}
//
//	public void setMenu_id(Long menu_id) {
//		this.menu_id = menu_id;
//	}

//	/**
//	 * コースリスト
//	 */
//	@OneToOne(mappedBy="corse", cascade=CascadeType.ALL)
//	private Corse corse;
//	
//	/**
//	 * メニューリスト
//	 */
//	@OneToMany(mappedBy="menu", cascade=CascadeType.ALL)
//	private List<Menu> menus = new ArrayList<>();
//
//	public Corse getCorse() {
//		return corse;
//	}
//
//	public void setCorse(Corse corse) {
//		this.corse = corse;
//	}
//
//	public List<Menu> getMenus() {
//		return menus;
//	}
//
//	public void setMenus(List<Menu> menus) {
//		this.menus = menus;
//	}
//
//	}		
}