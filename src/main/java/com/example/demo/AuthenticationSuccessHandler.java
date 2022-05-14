package com.example.demo;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandler
		implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

		// ロールがROLE_ADMINの場合、管理者専用画面に遷移
        if (roles.contains("ROLE_ADMIN")) {
        	response.sendRedirect(request.getContextPath() + "/admin");
        // ロールがROLE_USERの場合、アルバイト専用画面に遷移
        }else if(roles.contains("ROLE_USER")){
        	response.sendRedirect(request.getContextPath() + "/part");
        }
	}

}
