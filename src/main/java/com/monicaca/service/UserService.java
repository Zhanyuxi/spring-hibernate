package com.monicaca.service;

import com.monicaca.bean.User;
import com.monicaca.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	public User findByUserId(int userId) {  
        return userDAO.findById(User.class, userId);  
    }  
	public User findByUserName(User user) {
        return userDAO.findByName(user);
    }


    public List<User> findAll() {  
        return userDAO.findAll(User.class);  
    }
    @Transactional
    public int count() {
        return userDAO.count();
    }

    @Transactional  
    public void createUser(User user) {  
    	userDAO.save(user);  
    }  
  
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )

    public void updateUser(User user) {  
    	userDAO.update(user);  
    }  
  
    @Transactional  
    public void deleteUser(int userId) {  
    	userDAO.deleteById(User.class, userId);  
    }  
  
    @Transactional  
    public void deleteUser(User user) {  
    	userDAO.delete(user);  
    }  
}
