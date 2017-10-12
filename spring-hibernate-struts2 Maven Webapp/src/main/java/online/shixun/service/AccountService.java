/**
 * 
 */
package online.shixun.service;

import online.shixun.beans.Account;

/**
 * @ClassName: AccountService
 *
 * @Description: TODO Account服务接口类
 *
 * 
 * @author: 芸江
 *
 * @date 2017年9月25日 下午4:50:37
 */
public interface AccountService {

	/**
	 * 保存用户信息
	 * 
	 * @param account
	 *            Account 实例
	 *
	 * @return 返回true表示添加成功，false表示添加失败
	 */
	boolean updateAccount(Account account);

	/**
	 * 查询一页account数据记录
	 * 
	 * @return json格式的字符串
	 */
	String queryPageAccount(int page);

	/**
	 * 查询页数
	 * 
	 * @return 页数
	 */
	Long queryPages();

	/**
	 * 添加一个账户
	 * 
	 * @param account
	 *            Account 对象
	 */
	void addAccount(Account account);

	/**
	 * 查询用户信息，返回json
	 * 
	 * @param long1
	 * @return json格式的字符串
	 */
	String queryAccount(Long id);

	/**
	 * 查询用户名是否可用
	 * 
	 * @param username
	 *            用户名
	 * @return true用户可用，false用户名不可用
	 */
	boolean queryName(String username);

	/**
	 * 查询用户名是否可用
	 * 
	 * @param username
	 *            用户名
	 * @param id
	 *            用户id
	 * @return true用户名不可用，false用户名不可用
	 */
	boolean queryName(String username, Long id);

	/**
	 * @param id
	 */
	void deleteUser(Long id);

	/**
	 * 通过用户名模糊查询用户信息
	 * 
	 * @param username
	 *            用户名
	 * 
	 * @param page
	 *            查询的页码
	 * 
	 * @return 结果集转换的json格式的字符串
	 */
	String inquireUser(String username, int page);

	/**
	 * 模糊查询的总页数
	 * 
	 * @param username
	 *            查询的用户名
	 * 
	 * @return 模糊查询的总页数
	 */
	Long queryBlurryInquirePages(String username);

	/**
	 * 用户性别统计
	 * 
	 * @return 用户性别个数json格式字符串（先女后男）
	 * 
	 */
	String statisticsGender();

	/**
	 * 用户创建时间查询
	 * 
	 * @return json格式字符串
	 */
	String statisticsCreateData();

	/**
	 * 用户创建时间统计
	 * 
	 * @return json格式字符串
	 */
	String statisticsCreateDataCount();

}
