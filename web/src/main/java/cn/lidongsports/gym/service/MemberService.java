package cn.lidongsports.gym.service;

import cn.lidongsports.core.service.DefaultEntityManager;
import cn.lidongsports.gym.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService extends DefaultEntityManager<Member, Integer> {

	public List<Member> getByIds(String ids) {
		if (ids == null || ids.equals(";") || ids.equals("")) return null;
		List<Member> list =getEntityDao().createQuery(" from "+entityClass.getSimpleName()+" where id in "+cn.lidongsports.core.util.StrUtils.idStrToIds(ids)).list();
		return list;
	}
	
}
