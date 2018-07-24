package cn.lidongsports.system.web;

import cn.lidongsports.core.orm.StringPropertyFilter;
import cn.lidongsports.core.util.JSON;
import cn.lidongsports.core.util.web.ResponseUtils;
import cn.lidongsports.system.entity.GlobalType;
import cn.lidongsports.system.service.GlobalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/globalType")
public class GlobalTypeAction {

	@Autowired
	private GlobalTypeService globalTypeService;

	@RequestMapping("view")
	public String view() {
		return "system/globalType_view";
	}

	@RequestMapping("save")
	public String save(GlobalType globalType) {
		globalTypeService.saveOrUpdate(globalType);
		return "redirect:/dict/list";
	}

	@RequestMapping("list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		List<GlobalType> list = globalTypeService.search(filters);
		String json = new JSON(list).buildWithFilters(3);
		ResponseUtils.renderJson(response, json);
	}
}
