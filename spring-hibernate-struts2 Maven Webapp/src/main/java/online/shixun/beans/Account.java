package online.shixun.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @ClassName: Account
 *
 * @Description: TODO 实体类
 *
 * 
 * @author: 芸江
 *
 * @date 2017年9月25日 上午10:58:18
 */
@Entity
@Table(name = "account", catalog = "user")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "sex", nullable = false)
	private String sex;

	@Column(name = "caertedate", nullable = false)
	private Date caertedate;

	@Column(name = "modifydate", nullable = false)
	private Date modifydate;

	@Column(name = "isdelete", nullable = false)
	private Boolean isdelete;

	public Account() {
	}

	public Account(String username, String password, String email, String sex, Date caertedate, Date modifydate,
			Boolean isdelete) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.sex = sex;
		this.caertedate = caertedate;
		this.modifydate = modifydate;
		this.isdelete = isdelete;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getCaertedate() {
		return caertedate;
	}

	public void setCaertedate(Date caertedate) {
		this.caertedate = caertedate;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public Boolean getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Boolean isdelete) {
		this.isdelete = isdelete;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", sex="
				+ sex + ", caertedate=" + caertedate + ", modifydate=" + modifydate + ", isdelete=" + isdelete + "]";
	}

}