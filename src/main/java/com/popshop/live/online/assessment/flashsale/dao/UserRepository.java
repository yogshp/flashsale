package com.popshop.live.online.assessment.flashsale.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.popshop.live.online.assessment.flashsale.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByEmailId(String emailId);
}
