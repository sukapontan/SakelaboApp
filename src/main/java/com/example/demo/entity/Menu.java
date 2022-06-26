package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

/**
 * ユーザー情報 Entity
 */
@Entity
@Data
@Table(name = "menus")
public class Menu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * メニューID
	 */
	@Id
	@Column(name = "menu_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menu_id;
	
	/**
	 * メニュー名
	 */
	@Column(name = "menu_nm")
	@NotNull
	private String menu_nm;
	
	/**
	 * メニュー詳細
	 */
	@Column(name = "menu_dtl")
	@NotNull
	private String menu_dtl;
	
	/**
	 * 価格
	 */
	@Column(name = "price")
	@NotNull
	@Positive // 数値が正であることを検証する。
	private int price;

	/**
	 * メニュー種別
	 */
	@Column(name = "genre")
	@NotNull
	private String genre;
	
	/**
	 * 備考
	 */
	@Column(name = "note")
	private String note;
	
	/**
	 * 作成日
	 * 
	 */
	@Column(name = "ins_date")
	private String ins_date;
	
	/**
	 * 作成者
	 * 
	 */
	@Column(name = "ins_user")
	private String ins_user;
	
	/**
	 * 更新日
	 */
	@Column(name = "upd_date")
	private String upd_date;
	
	/**
	 * 更新者
	 */
	@Column(name = "upd_user")
	private String upd_user;
	
	/**
	 * 削除フラグ
	 */
	@Column(name = "del_flag")
	private String del_flag;
	
	public Long getMenu_id() {
		return menu_id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getIns_user() {
		return ins_user;
	}

	public void setIns_user(String ins_user) {
		this.ins_user = ins_user;
	}

	public String getUpd_user() {
		return upd_user;
	}

	public void setUpd_user(String upd_user) {
		this.upd_user = upd_user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_nm() {
		return menu_nm;
	}

	public void setMenu_nm(String menu_nm) {
		this.menu_nm = menu_nm;
	}

	public String getMenu_dtl() {
		return menu_dtl;
	}

	public void setMenu_dtl(String menu_dtl) {
		this.menu_dtl = menu_dtl;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getIns_date() {
		return ins_date;
	}

	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}

	public String getUpd_date() {
		return upd_date;
	}

	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

}