
package com.shiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiro.po.User;

/** 

* @author zb: 

* @createtime：2019年3月21日 上午8:56:28 

* 类说明 

*/

public interface UserRepository extends JpaRepository<User,String> {
	User findByUsername(String username);
}
