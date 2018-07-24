package cn.lidongsports;

import lombok.extern.log4j.Log4j;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

/**
 * @author kittaaron
 *         created by 2018/7/23
 */
//@EnableFeignClients
//@EnableDiscoveryClient
@SpringBootApplication
@Log4j
@ServletComponentScan
public class MainBootStrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(MainBootStrap.class).web(true).run(args);    }


    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){
        return hemf.getSessionFactory();
    }
}
