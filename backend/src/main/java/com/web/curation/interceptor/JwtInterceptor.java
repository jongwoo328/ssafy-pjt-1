package com.web.curation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.web.curation.config.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtUtil jwtutil;

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (!request.getMethod().equals("OPTIONS")) {
			
			String token = request.getHeader("Authorization");
			if (token != null) {
				try {
					Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(jwtutil.getKey()).build()
							.parseClaimsJws(token);
					Claims claims = jws.getBody();
					String email = (String) claims.get("email");
					jwtutil.setUserno((Integer)claims.get("userno"));
//					User user = new User();
//					user.setEmail(email);
//					request.setAttribute("userinfo", user);
					return true;
					
				} catch (JwtException e) {
					return false;
				}
			} else {
				return false;
			}
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
