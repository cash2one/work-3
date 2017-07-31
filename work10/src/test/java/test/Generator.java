package test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oa.entity.Menu;
import com.oa.service.MenuService;
import com.oa.service.impl.MenuServiceImpl;
/**
 * 
 * @author liliting
 *
 */
public class Generator {
	@Test
	public void test() throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File("src/main/resources/generator.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		System.out.println(cp);
		Configuration config = cp.parseConfiguration(configFile);
		System.out.println(config);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		System.out.println(callback);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
				callback, warnings);
		myBatisGenerator.generate(null);
		System.out.println("逆向工程");
		
		
//		ApplicationContext aContext = new  ClassPathXmlApplicationContext("applicationContext-dao.xml");
//		System.out.println();
//		MenuService menuService = (MenuService) aContext.getBean("menuServiceImpl");
//		System.out.println(menuService);
//		System.out.println(new MenuServiceImpl());
//		Menu menu = new Menu();
//		menu.setMenuName("bdkdkdhwdjwoi");
////		
//     	menuService.insert(menu);
		
		
		
	}
}
