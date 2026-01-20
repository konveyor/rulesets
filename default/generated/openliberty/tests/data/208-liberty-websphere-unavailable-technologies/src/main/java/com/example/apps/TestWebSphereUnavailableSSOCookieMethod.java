package com.example.apps;

import com.ibm.websphere.security.WSSecurityHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestWebSphereUnavailableSSOCookieMethod {
	public static void main(String[] args) {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		WSSecurityHelper.revokeSSOCookies(request, response);
	}
}
