
package com.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shiro.realm.CustomRealm;

/** 

 * @author zb: 

 * @createtime：2019年3月20日 下午2:59:41 

 * 类说明   shiro配置类

 */
@Configuration
public class ShiroConfig {
	/**
	 * 配置securityFilterFactoryBean,
	 * 注入shiroManager
	 * 设置无权限跳转
	 * 设置权限拦截器
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
		shiroFilterFactoryBean.setLoginUrl("/notLogin");
		// 设置无权限时跳转的 url;
		shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

		// 设置拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		//游客，开发权限
		filterChainDefinitionMap.put("/guest/**", "anon");
		//用户，需要角色权限 “user”
		filterChainDefinitionMap.put("/user/**", "roles[user]");
		//管理员，需要角色权限 “admin”
		filterChainDefinitionMap.put("/admin/**", "roles[admin]");
		//开放登陆接口
		filterChainDefinitionMap.put("/login", "anon");
		//其余接口一律拦截
		//主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
		filterChainDefinitionMap.put("/**", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		System.out.println("Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}

	/**
	 * 注入 securityManager
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(customRealm());
		//自定义缓存,使用redis
		securityManager.setCacheManager(redisCacheManager());
		//自定义session,使用redis
		securityManager.setSessionManager(SessionManager());
		return securityManager;
	}
	/**
	 * 身份认证realm
	 * @return
	 */
	@Bean
	public CustomRealm customRealm(){
		CustomRealm customRealm=new CustomRealm();
		//customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return customRealm;
	}
	
//	/**
//	 * 告诉shiro加密方式  -->不需要写,自己实现加密比较方便
//	 */
//	@Bean
//	public HashedCredentialsMatcher hashedCredentialsMatcher(){
//		HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
//		hashedCredentialsMatcher.setHashAlgorithmName("md5");
//		hashedCredentialsMatcher.setHashIterations(2);
//		return hashedCredentialsMatcher;
//	}
	
	
	/**
	 * 设置redismanager
	 */
	@Bean
	public RedisManager redisManager(){
		RedisManager redisManager=new RedisManager();
		redisManager.setHost("127.0.0.1");
		redisManager.setPort(6379);
		redisManager.setExpire(60);
		redisManager.setTimeout(1800);
		return redisManager;
	}
	/**
	 * 缓存,使用redis实现
	 * @return
	 */
	@Bean
	public RedisCacheManager redisCacheManager(){
		RedisCacheManager redisCacheManager=new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}
	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO(){
		RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}
	/**
	 * Session Manager
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public DefaultWebSessionManager SessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}

	/**
	 * 系统自带的Realm管理，主要针对多realm
	 * */
//	@Bean 
//	public ModularRealmAuthenticator modularRealmAuthenticator(){ 
//		//自己重写的ModularRealmAuthenticator 
//		UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator() ;
//		modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy()); 
//		return modularRealmAuthenticator ;
//	}
	
	
	
	





}





