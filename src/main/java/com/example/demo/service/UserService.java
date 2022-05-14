package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
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
		return userRepository.save(user);
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
}