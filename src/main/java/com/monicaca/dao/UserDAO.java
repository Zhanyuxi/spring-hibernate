package com.monicaca.dao;

import com.monicaca.bean.User;

public interface UserDAO extends BaseDAO{


     User findByName(User user);
     int count();
}
