package cn.lidongsports.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author kittaaron
 *         created by 2018/7/23
 */
public class HibernateConfig {
    public SessionFactory sessionFactory() {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactorn = configuration.buildSessionFactory();
        return sessionFactorn;
    }
}
