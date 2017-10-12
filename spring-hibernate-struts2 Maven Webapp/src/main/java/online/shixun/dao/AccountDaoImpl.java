
package online.shixun.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import online.shixun.beans.Account;

/**
 * @ClassName: AccountDaoImpl
 *
 * @Description: TODO Account数据库操作实现类
 *
 * 
 * @author: 芸江
 *
 * @date 2017年9月25日 上午11:00:25
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean updateAccountInfo(Account account) {

		sessionFactory.getCurrentSession().merge(account);

		return true;

	}

	@Override
	public List<Account> queryPageAccount(int page) {

		List<Account> accounts = sessionFactory.getCurrentSession().createQuery("from Account c where c.isdelete = 0")
				.setFirstResult((page - 1) * 3).setMaxResults(3).list();

		return accounts;
	}

	@Override
	public Long queryAccountCount() {
		return (Long) sessionFactory.getCurrentSession()
				.createQuery("select count(*) from Account c where c.isdelete = 0").uniqueResult();
	}

	@Override
	public void addAccoutInfo(Account account) {
		sessionFactory.getCurrentSession().save(account);
		return;
	}

	@Override
	public Account queryAccountInfo(Long id) {
		return sessionFactory.getCurrentSession().get(Account.class, id);
	}

	@Override
	public Long queryUsername(String username) {
		return (Long) sessionFactory.getCurrentSession()
				.createQuery("select count(*) from Account c where c.username=? and  c.isdelete = 0")
				.setParameter(0, username).uniqueResult();
	}

	@Override
	public List<Account> inquireUser(String username, int page) {

		return sessionFactory.getCurrentSession()
				.createQuery("from Account c where c.username like ? and  c.isdelete = 0")
				.setParameter(0, "%" + username + "%").setFirstResult((page - 1) * 3).setMaxResults(3).list();
	}

	@Override
	public Long queryBlurryInquirePages(String username) {

		return (Long) sessionFactory.getCurrentSession()
				.createQuery("select count(*) from Account c where c.username like ? and  c.isdelete = 0")
				.setParameter(0, "%" + username + "%").uniqueResult();
	}

	@Override
	public List statisticsGender() {
		return sessionFactory.getCurrentSession()
				.createQuery("select count(*) as c from Account a where a.isdelete = 0 group by a.sex").list();

	}

	@Override
	public List statisticsCreateData() {

		return sessionFactory.getCurrentSession()
				.createQuery("SELECT DISTINCT a.caertedate as c FROM Account a where a.isdelete=0").list();
	}

	@Override
	public List statisticsCreateDataCount() {

		return sessionFactory.getCurrentSession()
				.createQuery("select count(*) as c from Account a where a.isdelete=0 group by a.caertedate").list();
	}

}
