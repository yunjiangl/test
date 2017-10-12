package online.shixun.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import online.shixun.beans.Account;
import online.shixun.dao.AccountDao;

/**
 * @ClassName: AccountServiceImpl
 *
 * @Description: TODO 用户业务实现类
 *
 * 
 * @author: 芸江
 *
 * @date 2017年9月25日 下午4:52:50
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Resource
	private AccountDao accountDao;

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public boolean updateAccount(Account account) {
		try {
			Date time = formatter.parse(formatter.format(new Date()));
			account.setModifydate(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date time = accountDao.queryAccountInfo(account.getId()).getCaertedate();

		account.setCaertedate(time);

		account.setIsdelete(false);
		return accountDao.updateAccountInfo(account);

	}

	@Override
	public String queryPageAccount(int page) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		return gson.toJson(accountDao.queryPageAccount(page));
	}

	@Override
	public Long queryPages() {
		Long count = accountDao.queryAccountCount();
		Long pages = count / 3;

		if (count % 3 != 0) {
			pages++;
		}

		return pages;
	}

	@Override
	public void addAccount(Account account) {
		try {
			Date time = formatter.parse(formatter.format(new Date()));
			account.setCaertedate(time);
			account.setModifydate(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		account.setIsdelete(false);
		accountDao.addAccoutInfo(account);
		return;
	}

	@Override
	public String queryAccount(Long id) {
		Gson gson = new Gson();
		return gson.toJson(accountDao.queryAccountInfo(id));
	}

	@Override
	public boolean queryName(String username) {
		if (accountDao.queryUsername(username) > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean queryName(String username, Long id) {
		if (accountDao.queryAccountInfo(id).getUsername().equals(username)) {
			return true;
		} else {
			return this.queryName(username);
		}
	}

	@Override
	public void deleteUser(Long id) {
		Account account = accountDao.queryAccountInfo(id);
		account.setIsdelete(true);
		accountDao.updateAccountInfo(account);
		return;

	}

	@Override
	public String inquireUser(String username, int page) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(accountDao.inquireUser(username, page));
	}

	@Override
	public Long queryBlurryInquirePages(String username) {
		Long count = accountDao.queryBlurryInquirePages(username);
		Long pages = count / 3;

		if (count % 3 != 0) {
			pages++;
		}

		return pages;
	}

	@Override
	public String statisticsGender() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(accountDao.statisticsGender());
	}

	
	@Override
	public String statisticsCreateData() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(accountDao.statisticsCreateData());
	}

	
	@Override
	public String statisticsCreateDataCount() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(accountDao.statisticsCreateDataCount());
	}

}
