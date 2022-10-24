package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * メニュー情報 Entity
 */
@Entity
@Data
@Table(name = "information")
public class Information {

	/**
	 * インフォメーションID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long information_id;
	
	/**
	 * タイトル
	 */
	@Column(name = "title")	 
	@NotEmpty(message = "タイトルは必須です")
	private String title;
	
	/**
	 * お知らせ内容
	 */
	@Column(name = "cont")
	@NotEmpty(message = "お知らせ内容は必須です")
	private String cont;
	
	/**
	 * 作成日
	 */
	@Column(name = "ins_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime ins_date;
	
	/**
	 * 作成者
	 */
	@Column(name = "ins_user")
	private String ins_user;
	
	/**
	 * 更新日
	 */
	@Column(name = "upd_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime upd_date;
	
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
	
	public Long getInformation_id() {
		return information_id;
	}

	public void setInformation_id(Long information_id) {
		this.information_id = information_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public LocalDateTime getIns_date() {
		return ins_date;
	}

	public void setIns_date(LocalDateTime ins_date) {
		this.ins_date = ins_date;
	}

	public String getIns_user() {
		return ins_user;
	}

	public void setIns_user(String ins_user) {
		this.ins_user = ins_user;
	}

	public LocalDateTime getUpd_date() {
		return upd_date;
	}

	public void setUpd_date(LocalDateTime upd_date) {
		this.upd_date = upd_date;
	}

	public String getUpd_user() {
		return upd_user;
	}

	public void setUpd_user(String upd_user) {
		this.upd_user = upd_user;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

}
