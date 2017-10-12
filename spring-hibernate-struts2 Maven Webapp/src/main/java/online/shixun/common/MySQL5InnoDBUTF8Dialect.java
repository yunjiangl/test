package online.shixun.common;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * 
 * 设置mysql引擎为 InnoDB
 * 编码格式为utf-8
 * 
 * @author 芸江
 *
 */
public class MySQL5InnoDBUTF8Dialect extends MySQL5Dialect {

	@Override
	public String getTableTypeString() {
		return "ENGINE=InnoDB DEFAULT CHARSET=UTF8";
	}

}
