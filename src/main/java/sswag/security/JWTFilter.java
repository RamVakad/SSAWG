package sswag.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTFilter extends GenericFilterBean {

  private JWTProvider jwtProvider;

  public JWTFilter(JWTProvider jwtProvider) {
    this.jwtProvider = jwtProvider;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
      throws IOException, ServletException {
    
    String token = jwtProvider.resolveToken((HttpServletRequest) req);
    if (token != null && jwtProvider.validateToken(token)) {
      Authentication auth = token != null ? jwtProvider.getAuthentication(token) : null;
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(req, res);
  }

}
