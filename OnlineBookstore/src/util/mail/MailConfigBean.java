package util.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class MailConfigBean {
	private String userName;
	private String password;
	private String host;
	private String from;

	private String userConfigName = "";
	private String pwdConfigName = "";
	private String nickConfigName = "";

	public MailConfigBean(String type) {
		if (StringUtils.isNotBlank(type)) {
			userConfigName = "userName_" + type;
			pwdConfigName = "password_" + type;
			nickConfigName = "nickName_" + type;
		} else {
			userConfigName = "userName";
			pwdConfigName = "password";
			nickConfigName = "nickName";
		}
		load();
	}

	private void load() {
		InputStream in = null;
		try {
			in = this.getClass().getResourceAsStream("/mail_user_pwd.properties");
			Properties property = new Properties();
			property.load(in);
			userName = property.getProperty(userConfigName);
			password = property.getProperty(pwdConfigName);
			host = property.getProperty("host");
			from = property.getProperty(nickConfigName);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getHost() {
		return host;
	}

	public String getFrom() {
		return from;
	}

	@Override
	public String toString() {
		return "MailConfigBean [userName=" + userName + ", password=" + password + ", host=" + host + ", from=" + from + ", userConfigName=" + userConfigName
				+ ", pwdConfigName=" + pwdConfigName + ", nickConfigName=" + nickConfigName + "]";
	}
	

}
