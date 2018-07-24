package cn.lidongsports.system.web;

import cn.lidongsports.comm.vo.Msg;
import cn.lidongsports.core.orm.Page;
import cn.lidongsports.core.orm.PageRequest;
import cn.lidongsports.core.orm.StringPropertyFilter;
import cn.lidongsports.core.util.BeanUtil;
import cn.lidongsports.core.util.JSON;
import cn.lidongsports.core.util.web.ResponseUtils;
import cn.lidongsports.system.entity.Role;
import cn.lidongsports.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleAction {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("view")
	public String view() {
		return "system/role_view";
	}
	
	@RequestMapping("list")
	public void list(PageRequest pageRequest, HttpServletRequest request, HttpServletResponse response) {
		List<StringPropertyFilter> filters = StringPropertyFilter.buildFromHttpRequest(request);
		Page<Role> page = roleService.search2(pageRequest, filters);
		String json = new JSON(page).buildWithFilters(3);
		ResponseUtils.renderJson(response, json);
	}
	
	@ResponseBody
	@RequestMapping("save")
	public Msg save(Role role) {
		if (role.getId() == null) {
			roleService.saveOrUpdate(role);
		} else {
			Role orgRole = roleService.get(role.getId());
			try {
				BeanUtil.copyNotNullProperties(orgRole, role);
				roleService.saveOrUpdate(orgRole);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return new Msg(true);
	}
	
	@RequestMapping("get/{id}")
	public String get(@PathVariable("id")int id, Model model) {
		Role role = roleService.get(id);
		model.addAttribute("role", role);
		return "system/role_form";
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public Msg delete(String ids) {
		roleService.remove(ids);
		return new Msg(true);
	}
	
	@ResponseBody
	@RequestMapping("grant/{id}")
	public Msg grant(@PathVariable("id")int id, String menuIds, String menuNames) {
		Role role = roleService.get(id);
		role.setMenuIds(menuIds);
		role.setMenuNames(menuNames);
		roleService.saveOrUpdate(role);
		return new Msg(true);
	}
	
}
