package cn.lidongsports.gym.web;

import cn.lidongsports.comm.vo.Msg;
import cn.lidongsports.core.orm.Page;
import cn.lidongsports.core.orm.PageRequest;
import cn.lidongsports.core.orm.StringPropertyFilter;
import cn.lidongsports.core.util.BeanUtil;
import cn.lidongsports.core.util.JSON;
import cn.lidongsports.core.util.web.ResponseUtils;
import cn.lidongsports.gym.entity.Lockers;
import cn.lidongsports.gym.service.LockersService;
import cn.lidongsports.gym.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import org.apache.jasper.tagplugins.jstl.core.If;


@Controller
@RequestMapping("/lockers")
public class LockersAction {
	
	@Autowired
	private LockersService lockersService ;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("view")
	public String view() {
		return "gym/lockers_view";
	}
	
	@RequestMapping("list")
	public void list(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
		// 获得查询条件
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Lockers> page = lockersService.search2(pageRequest, filters);
		String json = new JSON(page).buildWithFilters(3);
		ResponseUtils.renderJson(response, json);		
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Msg save(Lockers lockers) {
		if(lockers.getMember().getId()==null){
			lockers.setMember(null);
		}
		if (lockers.getId() == null) {
			lockersService.saveOrUpdate(lockers);
		} else {
			Lockers orgLockers = lockersService.get(lockers.getId());
			try {
				BeanUtil.copyNotNullProperties(orgLockers, lockers);
				orgLockers.setBeginTime(lockers.getBeginTime());
				orgLockers.setEndTime(lockers.getEndTime());
				lockersService.saveOrUpdate(orgLockers);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return new Msg(true);
	}
	@RequestMapping("get/{id}")
	public String get(@PathVariable("id")int id, Model model) {
		Lockers lockers = lockersService.get(id);
		model.addAttribute("lockers", lockers);
		return "gym/lockers_form";
	}
	
	@ResponseBody
	@RequestMapping("del")
	public Msg delete(String ids) {
		lockersService.remove(ids);
		return new Msg(true);
	}
	
	@ResponseBody
	@RequestMapping("clear/{id}")
	public Msg clear(@PathVariable("id")int id) {
		lockersService.clear(id);
		return new Msg(true);
	}
	
	@RequestMapping("init")
	public void init() {
		for(int i=0;i<100;i++){
			Lockers lockers = new Lockers();
			lockers.setNo("G"+(100+i));
			lockersService.saveOrUpdate(lockers);
		}
	}
	@InitBinder
	public void initBinder1(WebDataBinder binder) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
	}

}
