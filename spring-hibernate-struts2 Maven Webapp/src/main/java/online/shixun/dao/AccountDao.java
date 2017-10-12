/**
 * 
 */
package online.shixun.dao;

import java.util.List;

import com.google.gson.JsonElement;

import online.shixun.beans.Account;

/**
 * @ClassName: AccountDao
 *
 * @Description: TODO Account数据库操作接口类
 *
 * 
 * @author: 芸江
 *
 * @date 2017年9月25日 上午10:57:58
 */
public interface AccountDao {

	/**
	 * 更新数据库数据
	 * 
	 * @param account
	 *            Account 实例
	 * 
	 * @return 返回false表示添加失败，返回true表示添加成功
	 */
	boolean updateAccountInfo(Account account);

	/**
	 * 分页查询account数据
	 * 
	 * @return Account集合，保存一页的account记录，如果返回null表示查询失败，或者数据库中没有任何account记录
	 */
	List<Account> queryPageAccount(int page);

	/**
	 * 查询用户记录行数
	 * 
	 * @return 用户记录数
	 */
	Long queryAccountCount();

	/**
	 * 添加一个用户信息
	 * 
	 * @param account
	 *            Account 对象
	 */
	void addAccoutInfo(Account account);

	/**
	 * 查询用户信息
	 * 
	 * <p>
	 * 通过用户id查询用户信息
	 * </p>
	 * 
	 * @param id
	 *            用户id
	 * @return Account 对象
	 */
	Account queryAccountInfo(Long id);

	/**
	 * 查询用户信息
	 * 
	 * <p>
	 * 通过用户名查询用户信息
	 * </p>
	 * 
	 * @param username
	 *            用户名
	 * @return 返回查询结果行数
	 */
	Long queryUsername(String username);

	/**
	 * 通过用户名模糊查询用户数据 ,并分页显示
	 * 
	 * @param username
	 *            用户名
	 * @param page
	 *            查询的页数
	 * @return Account 集合
	 */
	List<Account> inquireUser(String username, int page);

	/**
	 * 查询模糊查询的总页数
	 * 
	 * @param username
	 *            查询的用户名
	 * 
	 * @return 返回查询结果行数
	 */
	Long queryBlurryInquirePages(String username);

	/**
	 * 统计性别
	 * @return  统计结果
	 */
	List statisticsGender();

	/**
	 * 用户创建时间查询
	 * 
	 * @return 查询结果集合
	 */
	List statisticsCreateData();

	/**
	 * 用户创建时间统计
	 * 
	 * @return 统计结果集合
	 */
	List statisticsCreateDataCount();

}
