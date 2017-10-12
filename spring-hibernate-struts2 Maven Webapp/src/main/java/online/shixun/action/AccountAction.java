/**
 * 
 */
package online.shixun.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;

import online.shixun.beans.Account;
import online.shixun.service.AccountService;

/**
 * @ClassName: AccountAction
 *
 * @Description: TODO Account动作类
 *
 * 
 * @author: 芸江
 *
 * @date 2017年9月25日 下午5:03:46
 */
@Namespace("/")
@ParentPackage("struts-default")
public class AccountAction {

	@Autowired
	private AccountService accountService;

	private int page;

	private Long id;

	private Account account;

	private String username;

	public void setaccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 分页数据
	 * 
	 * @throws IOException
	 */
	@Action("userList")
	public void userList() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		out.write(accountService.queryPageAccount(page));

		return;
	}

	/**
	 * 总页数
	 * 
	 * @throws IOException
	 */
	@Action("userPages")
	public void userPages() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.write(accountService.queryPages().toString());

		return;
	}

	/**
	 * 模糊查询的用户总页数
	 * 
	 * @throws IOException
	 */
	@Action("userInquirePages")
	public void userInquirePages() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		out.write(accountService.queryBlurryInquirePages(username).toString());

		return;
	}

	/**
	 * 在编辑之前获得用户id
	 */
	@Action("userId")
	public void editUser() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("userId", id);
		return;
	}

	/**
	 * 删除用户信息
	 */
	@Action("deleteUser")
	public void deleteUser() {
		accountService.deleteUser(id);
		return;
	}

	/**
	 * 模糊查询用户信息
	 * 
	 * @throws IOException
	 */
	@Action("Inquire")
	public void inquire() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(accountService.inquireUser(username, page));
		return;
	}

	/**
	 * 清除id
	 */
	@Action("clean")
	public void clean() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("userId");
		return;
	}

	/**
	 * 发送User信息
	 * 
	 * @throws IOException
	 */
	@Action("getUser")
	public void getUser() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		if (session.get("userId") != null) {
			response.getWriter().write(accountService.queryAccount((Long) session.get("userId")));
			return;
		} else {
			response.getWriter().write("no");
			return;
		}
	}

	/**
	 * 查询用户名是否可用
	 * 
	 * @throws IOException
	 */
	@Action("username")
	public void username() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");

		Boolean flag = false;

		if (session.get("userId") != null) {
			if (accountService.queryName(username, (Long) session.get("userId"))) {
				flag = true;
			}
		} else {
			if (accountService.queryName(username)) {
				flag = true;
			}
		}

		response.getWriter().write(flag.toString());

		return;
	}

	/**
	 * 添加或更新用户信息
	 * 
	 * @throws IOException
	 */
	@Action("updateOrAddUser")
	public void updateOrAddUser() throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");

		if (session.get("userId") != null) {
			account.setId((Long) session.get("userId"));
			session.remove("userId");
			accountService.updateAccount(account);
			response.getWriter().write("修改成功");
		} else {
			accountService.addAccount(account);
			response.getWriter().write("添加成功");
		}
		return;
	}

	@Action("statisticsGender")
	public void statisticsGender() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(accountService.statisticsGender());
		return;
	}
	
	@Action("statisticsCreateData")
	public void statisticsCreateData() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(accountService.statisticsCreateData());
		return;
	}
	
	@Action("statisticsCreateDataCount")
	public void statisticsCreateDataCount() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(accountService.statisticsCreateDataCount());
		return;
	}

}
