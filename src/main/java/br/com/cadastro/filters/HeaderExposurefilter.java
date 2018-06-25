package br.com.cadastro.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class HeaderExposurefilter implements Filter{


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.addHeader("access-control-expose-header", "Authorization");
		filter.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
