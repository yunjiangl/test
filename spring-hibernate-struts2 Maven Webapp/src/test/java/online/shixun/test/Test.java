/**
 * 
 */
package online.shixun.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import online.shixun.beans.Account;
import online.shixun.dao.AccountDao;

/**
 * @ClassName: Test
 *
 * @Description: TODO
 *
 * 
 * @author: 芸江
 *
 * @date 2017年9月26日 下午3:33:26
 */
public class Test {

	@org.junit.Test
	public void testAdd() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		AccountDao accountDao = (AccountDao) context.getBean("accountDao");
		
		int i = 0;
		int j = 1;
		i = j++;
		System.out.println(i);
	}

}
