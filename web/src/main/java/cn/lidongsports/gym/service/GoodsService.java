package cn.lidongsports.gym.service;

import cn.lidongsports.core.service.DefaultEntityManager;
import cn.lidongsports.gym.entity.Goods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GoodsService extends DefaultEntityManager<Goods, Integer> {

}
