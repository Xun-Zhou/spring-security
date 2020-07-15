package com.example.demo.manage;

import com.example.demo.moudle.PermissionInfo;
import com.example.demo.moudle.RoleInfo;
import com.example.demo.service.PermissionServiceImpl;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 此方法判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 MyAccessDecisionManager的decide 方法，
     * 如果不在权限表中则放行。
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String url = ((FilterInvocation) o).getRequestUrl();
        String method = ((FilterInvocation) o).getRequest().getMethod();
        //REST风格  根据方法判断权限
        String authPath = url + " " + method;
        PathMatcher pathMatcher = new AntPathMatcher();
        for (PermissionInfo permissionInfo : PermissionServiceImpl.PERMISSIONS) {
            if (pathMatcher.match(permissionInfo.getPath() + " " + permissionInfo.getMethod(), authPath)) {
                List<RoleInfo> roleInfoList = permissionInfo.getRoleInfos();
                String[] roleIds = new String[roleInfoList.size()];
                for (int i = 0; i < roleInfoList.size(); i++) {
                    roleIds[i] = roleInfoList.get(i).getId();
                }
                return SecurityConfig.createList(roleIds);
            }
        }
        return SecurityConfig.createList("ROLE_DEF");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return Collections.emptyList();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
