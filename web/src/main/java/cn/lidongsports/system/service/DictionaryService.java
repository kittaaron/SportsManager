package cn.lidongsports.system.service;

import cn.lidongsports.core.service.DefaultEntityManager;
import cn.lidongsports.system.entity.Dictionary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DictionaryService extends DefaultEntityManager<Dictionary, Integer> {
	
	public List<Dictionary> findByItemIds(String ids) {
		String[] idArr = ids.split("\\s");
		List<Integer> idList = new ArrayList<Integer>();
		for (String id : idArr) {
			idList.add(Integer.parseInt(id));
		}
		return getEntityDao().get(idList);
	}

	public List<Dictionary> findByItemName(String itemName) {
		String hql = "from Dictionary where itemName=? order by sn asc, id asc";
		return getEntityDao().find(hql, itemName);
	}
	
}
