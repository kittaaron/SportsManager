package cn.lidongsports.gym.service;

import cn.lidongsports.core.service.DefaultEntityManager;
import cn.lidongsports.gym.entity.Lockers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LockersService extends DefaultEntityManager<Lockers, Integer> {

	public void clear(int id) {
		Lockers lockers = get(id);
		lockers.setAmount(null);
		lockers.setBeginTime(null);
		lockers.setEndTime(null);
		lockers.setMember(null);
		saveOrUpdate(lockers);	
	}

	

}
