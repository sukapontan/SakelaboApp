package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * コース情報 Entity
 */
@Entity
@Data
@Table(name = "corses")
public class Corse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name="corse_id", insertable = false, updatable = false)
	private Corse corse;
	
	/**
	 * コースID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long corse_id;
	
	/**
	 * コース名
	 */
	@Column(name = "corse_nm")
	@NotNull
	private String corse_nm;
	
	/**
	 * 価格
	 */
	@Column(name = "price")
	@NotNull
	private int price;
	
	/**
	 * 作成日
	 */
	@Column(name = "ins_date")
	@NotNull
	private String ins_date;
	
	/**
	 * 更新日
	 */
	@Column(name = "upd_date")
	@NotNull
	private String upd_date;
	
	public Long getCorse_id() {
		return corse_id;
	}

	public void setCorse_id(Long corse_id) {
		this.corse_id = corse_id;
	}

	public String getCorse_nm() {
		return corse_nm;
	}

	public void setCorse_nm(String corse_nm) {
		this.corse_nm = corse_nm;
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
}
