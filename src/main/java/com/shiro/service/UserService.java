
package com.shiro.service;

import com.shiro.po.User;

/** 

 * @author zb: 

 * @createtime：2019年3月21日 上午9:00:52 

 * 类说明 

 */

public interface UserService {
	User findByUsername(String username);
}
