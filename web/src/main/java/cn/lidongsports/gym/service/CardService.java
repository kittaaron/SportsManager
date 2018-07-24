package cn.lidongsports.gym.service;

import cn.lidongsports.core.service.DefaultEntityManager;
import cn.lidongsports.gym.entity.Card;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CardService extends DefaultEntityManager<Card, Integer> {

	public List<Card> getByIds(String ids) {
		if (ids == null || ids.equals(";") || ids.equals("")) {
			return null;
		}
		List<Card> list =getEntityDao().createQuery(" from "+entityClass.getSimpleName()+" where id in "+cn.lidongsports.core.util.StrUtils.idStrToIds(ids)).list();
		return list;
	}

}
