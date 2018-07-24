package cn.lidongsports.core.mvc;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

public class DateWebBindingInitializer implements WebBindingInitializer {

	/**
	 * 可以注册多个属性编辑器
	 * */
	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());		
	}

}
