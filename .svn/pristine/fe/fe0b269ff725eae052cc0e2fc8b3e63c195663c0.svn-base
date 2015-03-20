package org.kanwang.hw10;

import javax.servlet.http.HttpServletRequest;

import com.googlecode.mgwt.linker.server.propertyprovider.PropertyProviderBaseImpl;
import com.googlecode.mgwt.linker.server.propertyprovider.PropertyProviderException;

public class MyMobileUserAgentProvider extends PropertyProviderBaseImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7478122098836802106L;

	@Override
	public String getPropertyName() {
		return "mobile.user.agent";
	}

	@Override
	public String getPropertyValue(HttpServletRequest req) throws PropertyProviderException {
		String ua = getUserAgent(req);

		if (ua.contains("android")) {
			return "mobilesafari";
		}
		if (ua.contains("iphone")) {
			return "not_mobile";
		}
		if (ua.contains("ipad")) {
			return "not_mobile";
		}
		if (ua.contains("blackberry")) {
			return "not_mobile";
		}
		return "not_mobile";
	}

}
