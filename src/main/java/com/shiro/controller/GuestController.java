
package com.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.vo.ResultMap;



/** 

* @author zb: 

* @createtime：2019年3月20日 下午3:34:09 

* 类说明 

*/

@RestController
@RequestMapping("/guest")
public class GuestController{
     
    private ResultMap resultMap=new ResultMap();

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public ResultMap login() {
        return resultMap.success().message("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap submitLogin() {
        return resultMap.success().message("您拥有获得该接口的信息的权限！");
    }
}