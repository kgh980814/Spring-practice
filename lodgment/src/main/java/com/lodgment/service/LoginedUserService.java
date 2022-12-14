package com.lodgment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lodgment.domain.Category;
import com.lodgment.domain.User;
import com.lodgment.mapper.CategoryMapper;
import com.lodgment.mapper.UserMapper;


@Service
@Transactional
public class LoginedUserService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	public List<Category> getAllCategories() {
		return categoryMapper.getAllCategories();
	}
	
	public User updateNickname(String userId, String nickname) {
		User user = userMapper.getUserById(userId);
		user.setNickname(nickname);
		
		userMapper.update(user);
		
		return user;
	}

	public User updateName(String userId, String name) {
		User user = userMapper.getUserById(userId);
		user.setName(name);
		
		userMapper.update(user);
		
		return user;
	}

	public User updateTel(String userId, String tel) {
		User user = userMapper.getUserById(userId);
		user.setTel(tel);
		
		userMapper.update(user);
		
		return user;
	}

	public User updatePassword(String id, String password) {
		User user = userMapper.getUserById(id);
		user.setPassword(password);
		
		userMapper.update(user);
		
		return user;
	}
	
}
