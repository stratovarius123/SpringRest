package br.com.cadastro.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	@Autowired
	JWTUtil jwtutil;
	
	@Autowired
	UserDetailsService userdetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtutil, UserDetailsService userdetailsService) {
		super(authenticationManager);
		this.jwtutil = jwtutil;
		this.userdetailsService = userdetailsService;
	}

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{
		String tokenHeader = request.getHeader("Authorization");
		if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
			UsernamePasswordAuthenticationToken ath = getAuthentication(request,tokenHeader.substring(7));
			if(ath != null){
				SecurityContextHolder.getContext().setAuthentication(ath);
			}
		}
		
		chain.doFilter(request, response);
	}


	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String substring) {
		if(jwtutil.tokenValido(substring)){
			String username = jwtutil.getUserName(substring);
			UserDetails user = userdetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}
}
