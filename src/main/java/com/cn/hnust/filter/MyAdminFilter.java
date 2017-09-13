package com.cn.hnust.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyAdminFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		System.out.println("name " + name + " password " + password + " ssid " + session.getId() + "user "
				+ session.getAttribute("user"));
		if (name != null && password != null) {
			if (name.equals("wanglining") && password.equals("hahawang")) {
				session.setAttribute("user", "wanglining");
				if (request.getRequestURI().indexOf("login.html") != -1) {
					response.sendRedirect("index");
				}
			}
		}
		String re = request.getHeader("x-requested-with");
		System.out.println(re);
		if (session.getAttribute("user") == null && request.getRequestURI().indexOf("login.html") == -1) {
			// response.sendRedirect("login.html");
			System.out.println("entry dispatcher");
			request.getRequestDispatcher("login.html").forward(arg0, arg1);
			return;
		}
		arg2.doFilter(arg0, arg1);
	}

	// 判断是否是ajax请求
	private boolean ajaxDofilterSessionNull(HttpServletRequest request, HttpServletResponse response) {
		boolean isAjax = false;
		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
			// ajax请求
			response.setHeader("sessionstatus", "timeout");
			isAjax = true;
		}
		return isAjax;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
