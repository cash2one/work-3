package test;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 验证账号密码
 * 发送邮件必须的步骤
 * @author Administrator
 *
 */
public class EmailAutherticator  extends Authenticator{
	
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication("1512018642@qq.com","ivgayqvdsnirhgbh");
        }
        
    }
 
