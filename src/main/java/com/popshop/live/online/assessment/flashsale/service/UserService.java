package com.popshop.live.online.assessment.flashsale.service;

import org.springframework.stereotype.Service;

import com.popshop.live.online.assessment.flashsale.model.User;

@Service
public interface UserService {
	public User createUser(User user);
	public User getUser(Long id);
	public User updateUser(User user, Long id);
	public void deleteUser(Long id);
}
