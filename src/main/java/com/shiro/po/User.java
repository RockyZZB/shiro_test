
package com.shiro.po;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/** 

* @author zb: 

* @createtime：2019年3月21日 上午8:53:59 

* 类说明 

*/

@Data
@Entity
public class User {
	@Id
	private String id;
	
	private String username;
	
	private String password;
	
	private String role;

}
