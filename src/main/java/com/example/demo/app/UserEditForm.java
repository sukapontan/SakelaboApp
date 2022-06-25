package com.example.demo.app;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserEditForm {
	
	/**
	 * ID
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(min = 1, max = 50, message = "ユーザー名は1文字以上50文字以下で入力してください")	
	private String username;
	@NotBlank
	@Size(min = 6, max = 20, message = "パスワードは6文字以上20文字以下で入力してください")
    private String password;
	@NotBlank
	private String authority;

}
