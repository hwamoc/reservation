package kr.or.connect.reservation.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dto.Product;

public class ProductDaoTest {

	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		ProductDao productDao = ac.getBean(ProductDao.class);
		
		
		List<Product> list = productDao.selectAll(1, 1, 4);
		Product product = list.get(1);
		System.out.println(product);
		
//		LogDao logDao = ac.getBean(LogDao.class);
//		Log log = new Log();
//		log.setIp("127.0.0.1");
//		log.setMethod("insert");
//		log.setRegdate(new Date());
//		logDao.insert(log);
	}

}
