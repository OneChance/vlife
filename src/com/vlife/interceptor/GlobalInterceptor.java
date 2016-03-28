package com.vlife.interceptor;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.vlife.account.entity.Account;
import com.vlife.account.service.AccountService;
import com.vlife.tool.JsonTool;
import com.vlife.tool.WebUtil;

public class GlobalInterceptor implements HandlerInterceptor {

	public static final String ENVIRONMENT_REQUEST_NAME = "env";

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handlerMethod)
			throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		if (!this.isResources(handlerMethod)) {

			String local = WebUtil.getCookies(request, "vlife_lan");

			Locale locale = LocaleContextHolder.getLocale();

			if (local != null && !local.equals("")) {
				if (local.equals("zh")) {
					locale = new Locale("zh", "CN");
				} else if (local.equals("en")) {
					locale = new Locale("en", "US");
				}
			}

			request.getSession()
					.setAttribute(
							SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,
							locale);

			Account account = (Account) request.getSession().getAttribute(
					"loginAccount");

			if (null == account) {
				String userid = WebUtil.getCookies(request, "vlife_uinfo");

				if (null != userid && !userid.equals("")
						&& !userid.equals("-1")) {
					account = accountService.getUser(userid);
				}
			}

			if (account != null) {
				request.getSession().setAttribute("loginAccount", account);
			}

		}

		return true;
	}

	/**
	 * Controller interceptor after method run
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handlerMethod,
			ModelAndView modelAndView) throws Exception {

		response.setCharacterEncoding("UTF-8");

		if (!this.isResources(handlerMethod)) {
			if (isJson(handlerMethod)) {
				if (null != modelAndView && null != modelAndView.getViewName()) {
					for (Object o : modelAndView.getModelMap().values()) {
						if (o instanceof JsonTool) {
							((JsonTool) o).write(response);
							modelAndView.clear();
						}
					}
				}
			}
		}

		this.configSystemEnvironment(request);
	}

	/**
	 * Controller interceptor after completion
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handlerMethod, Exception e)
			throws Exception {
	}

	/**
	 * Check is resources
	 */
	public boolean isResources(Object handlerMethod) {
		if (handlerMethod instanceof ResourceHttpRequestHandler) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isJson(Object handlerMethod) {
		if (null == handlerMethod
				|| null == ((HandlerMethod) handlerMethod).getMethod()) {
			return false;
		}
		Class c = ((HandlerMethod) handlerMethod).getMethod().getReturnType();
		if (c == JsonTool.class) {
			return true;
		}
		return false;
	}

	public void configSystemEnvironment(HttpServletRequest request) {
		Map<String, Object> env = new HashMap<String, Object>();
		String resourcesUrl = request.getRequestURI() + "resources";
		env.put("resourcesUrl", resourcesUrl);
		env.put("baseUrl", request.getRequestURI());

		request.setAttribute(ENVIRONMENT_REQUEST_NAME, env);
	}

	@Resource
	private AccountService accountService;

}