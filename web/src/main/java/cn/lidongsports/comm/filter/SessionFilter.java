package cn.lidongsports.comm.filter;

import cn.lidongsports.system.entity.AppUser;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 判断用户是否过期
 */
public class SessionFilter implements Filter {

	private static String[] exclude = { "/css/", "/img/", "/fonts/", "/js/",
			"/ajax/", "/user/login", "/attachFiles", "/user/login",
			"/login.html", "/main.jsp" };

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String url = request.getServletPath();
		if (StringUtils.startsWithAny(url, exclude)) {
			chain.doFilter(req, resp);
			return;
		}

		AppUser user = (AppUser) request.getSession().getAttribute("USER");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.html");
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
