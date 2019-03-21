
package com.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.vo.ResultMap;

/** 

* @author zb: 

* @createtime：2019年3月20日 下午4:09:05 

* 类说明 

*/
@RestController
@RequestMapping("/user")
public class UserController {
    private ResultMap resultMap=new ResultMap();

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage() {
        return resultMap.success().message("您拥有用户权限，可以获得该接口的信息！");
    }
}