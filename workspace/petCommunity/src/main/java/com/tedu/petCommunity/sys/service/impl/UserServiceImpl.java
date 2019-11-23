package com.tedu.petCommunity.sys.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.sys.dao.PetcUserDao;
import com.tedu.petCommunity.sys.entity.PetcUserPO;
import com.tedu.petCommunity.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private PetcUserDao petcUserDao;

	@Override
	public int updatePetcUser(PetcUserPO petcUserPO, Integer id) {
		if (petcUserPO == null)
			throw new IllegalArgumentException("保存对象不能为空");
		if (StringUtils.isEmpty(petcUserPO.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if (StringUtils.isEmpty(petcUserPO.getNickname()))
			throw new IllegalArgumentException("昵称不能为空");
		if (StringUtils.isEmpty(petcUserPO.getPetType()))
			throw new IllegalArgumentException("宠物类型不能为空");
		if ((Pattern.matches("\\w+@(\\w+.)+[a-z]{2,3}", petcUserPO.getEmail())))
			throw new IllegalArgumentException("输入的邮箱不合法");
		int rows = petcUserDao.updateUserInfo(petcUserPO);
		return rows;
	}

	@Override
	public Map<String, Object> findUserInfoById(Integer userId) {
		// 验证参数合法性
		if (userId == null || userId <= 0)
			throw new ServiceException("参数数据不合法,userId=" + userId);
		PetcUserPO user = petcUserDao.findUserInfo(userId);
		if (user == null)
			throw new ServiceException("用户不存在");
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		return map;
	}

	@Override
	public int insertAll(PetcUserPO data) {
		//1参数校验
		if(data == null)
			throw new ServiceException("参数不能为空");
		if (StringUtils.isEmpty(data.getUsername()))
			throw new ServiceException("用户名不能为空");
		if (StringUtils.isEmpty(data.getPassword()))
			throw new ServiceException("密码不能为空");
		if (StringUtils.isEmpty(data.getMobile()))
			throw new ServiceException("手机号码不能为空");
		
		//2验证用户已存在
		//2.1查询数据库中,username=用户注册输入的username有多少条list(select id from user where username=#{username})
		//2.2如果(list==null||list.size()==0),说明数据库中没有此用户名的用户,可以注册
		//2.3否则报错("此用户已被注册,请修改用户名")
		int row = petcUserDao.existName(data.getUsername());
		if(row>0)
			throw new ServiceException("用户名已存在");
		//3保存用户关系
		int rows = petcUserDao.insertAll(data);
		if (rows < 1)
			throw new ServiceException("注册用户失败");
		
		return rows;
			
		
		//3.1
	}

}
