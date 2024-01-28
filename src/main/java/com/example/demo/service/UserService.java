package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.form.PasswordResetForm;
import com.example.demo.repository.UserRepository;

/**
 * ユーザー情報 Service
 */
@Service
public class UserService {

	/**
	 * ユーザー情報 Repository
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * ユーザー情報 全検索
	 * 
	 * @return 検索結果
	 */
	public List<User> searchAll() {
		return userRepository.findAll();
	}

	/**
	 * ユーザー情報更新
	 * 
	 * @return
	 */
	public User updateUser(User user) {
		userRepository.update(user.getName(), user.getAuthority());
		return user;
	}

	/**
	 * ユーザー情報削除
	 * 
	 * @return
	 */
	public Optional<User> deleteUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		userRepository.deleteById(id);
		return user;
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User save(User user, String rawPassword){

	    String encodedPassword = passwordEncoder.encode(rawPassword);
	    user.setPassword(encodedPassword);
	    return userRepository.save(user);
	}

	public void updateUser(PasswordResetForm passwordResetForm) {
		String encodedPassword = passwordEncoder.encode(passwordResetForm.getPassword());
		System.out.println(encodedPassword);
		passwordResetForm.setPassword(encodedPassword);
		userRepository.save(passwordResetForm.getUserName(), encodedPassword);
	    return;
	}
}