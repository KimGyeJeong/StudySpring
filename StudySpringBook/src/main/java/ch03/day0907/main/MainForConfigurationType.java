package ch03.day0907.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import ch03.day0907.config.AppConf1;
import ch03.day0907.config.AppConf2;

public class MainForConfigurationType {

	public static void main(String[] args) throws Exception {
		AbstractApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);

		AppConf1 appConf1 = ctx.getBean(AppConf1.class);
		System.out.println(appConf1 != null);

		ctx.close();
	}

}