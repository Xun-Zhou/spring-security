package com.example.demo.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.constant.Constants;
import com.example.demo.exception.CommonException;
import com.example.demo.moudle.Response;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private static final String FILTER_APPLIED = "__spring_security_JWTAuthenticationFilter_filterApplied";

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //解决同一请求，两次经过过滤器  原因：过滤器被加载WebSecurityConfig和spring都加载了
        if (request.getAttribute(FILTER_APPLIED) != null) {
            chain.doFilter(request, response);
            return;
        }
        request.setAttribute(FILTER_APPLIED, true);
        // token
        String token = request.getHeader(Constants.TOKEN_HEADER);
        // 如果请求头中不存在 添加无权限访客账户
        if (StringUtils.isEmpty(token)) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            String username = "guest";
            User principal = new User(username, "", authorities);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (CommonException e) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().println(JSON.toJSONString(Response.error("400", "TOKEN ERROR")));
            response.getWriter().flush();
            return;
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) throws CommonException {
        // 解析token
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(Constants.SECURITY_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new CommonException(400, "解析token错误");
        }
        //获取用户名
        String username = claims.getSubject();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //获取当前用户角色
        String authority = claims.get(Constants.AUTHORITIES).toString();
        if (!StringUtils.isEmpty(authority)) {
            JSONArray list = JSON.parseArray(authority);
            for (int i = 0; i < list.size(); i++) {
                authorities.add(new SimpleGrantedAuthority(list.getString(i)));
            }
            if (!StringUtils.isEmpty(username)) {
                User principal = new User(username, "", authorities);
                return new UsernamePasswordAuthenticationToken(principal, null, authorities);
            }
        }
        return null;
    }
}

