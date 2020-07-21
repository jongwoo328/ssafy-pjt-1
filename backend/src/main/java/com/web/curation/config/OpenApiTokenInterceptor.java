package com.web.curation.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.servlet.HandlerInterceptor;

public class OpenApiTokenInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(OpenApiTokenInterceptor.class);
	private static final String HEADER_AUTH = "X-Auth-Token";
	
	@Autowired
	TokenService tokenService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("in Interceptor");
		String tokenString = request.getHeader(HEADER_AUTH);
		if (tokenString != null) {
			logger.info(tokenString);
			return true;
		}
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		return false;
	}
}
