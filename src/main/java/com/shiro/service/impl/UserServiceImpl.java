
package com.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.shiro.po.User;
import com.shiro.repository.UserRepository;
import com.shiro.service.UserService;

/** 

* @author zb: 

* @createtime：2019年3月21日 上午9:01:17 

* 类说明 

*/
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository repository;
	
	@Override
	@Cacheable(value="user")
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		User user=new User();
		if (username!=null) {
			user = repository.findByUsername(username);
		}
		return user;
	}
	

}
