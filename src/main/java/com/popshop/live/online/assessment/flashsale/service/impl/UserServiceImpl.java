package com.popshop.live.online.assessment.flashsale.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popshop.live.online.assessment.flashsale.dao.UserRepository;
import com.popshop.live.online.assessment.flashsale.model.User;
import com.popshop.live.online.assessment.flashsale.service.UserService;

@Component
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public User createUser(User user) {
		User userExists = userRepository.findByEmailId(user.getEmailId());
		if(userExists != null) {
			return null;
		}
		else {
			return userRepository.save(user);
		}
	}

	@Override
	public User getUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	@Override
	public User updateUser(User user, Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User userToUpdate = optionalUser.get();
			//userToUpdate.setName(user.getName());
			//userToUpdate.setEmailId(user.getEmailId());
			userToUpdate.setBalance(user.getBalance());
			return userRepository.save(userToUpdate);
		} else {
			return null;
		}
	}

	@Override
	public void deleteUser(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.deleteById(id);
		} 
	}
}
