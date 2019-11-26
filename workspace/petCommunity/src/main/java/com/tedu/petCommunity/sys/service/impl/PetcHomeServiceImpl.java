package com.tedu.petCommunity.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.petCommunity.common.exception.ServiceException;
import com.tedu.petCommunity.common.util.ShiroUtils;
import com.tedu.petCommunity.sys.dao.PetcActivityDao;
import com.tedu.petCommunity.sys.dao.PetcCommunityDao;
import com.tedu.petCommunity.sys.entity.PetcActivityPO;
import com.tedu.petCommunity.sys.entity.PetcCommunityPO;
import com.tedu.petCommunity.sys.service.PetcHomeService;
import com.tedu.petCommunity.sys.vo.PetcHomeContentVO;

/**
 * @author 阳昊 2019年11月26日 上午2:20:50
 */
@Service
public class PetcHomeServiceImpl implements PetcHomeService {

	@Autowired
	PetcCommunityDao communityDao;

	@Autowired
	PetcActivityDao activityDao;

	@Override
	public PetcHomeContentVO<?> getContentByType(String type) {
		Integer userId = ShiroUtils.getUserId();
		if ("comm".equals(type)) {
			List<PetcCommunityPO> list = communityDao.findCommunitys(userId, 0, 20);
			return new PetcHomeContentVO<PetcCommunityPO>("chat", list);
		} else if ("acti".equals(type)) {
			List<PetcActivityPO> list = activityDao.findActivitys(userId, 0, 20);
			return new PetcHomeContentVO<PetcActivityPO>("activity", list);
		} else {
			throw new ServiceException("参数错误");
		}
	}

}
