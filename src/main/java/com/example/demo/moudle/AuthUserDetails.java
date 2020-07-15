package com.example.demo.moudle;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * SpringSecurity需要的用户详情
 */
public class AuthUserDetails implements UserDetails {

    private String userId;

    private String userName;

    private String passWord;

    private String status;

    private List<RoleInfo> roleInfos;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RoleInfo> getRoleInfos() {
        return roleInfos;
    }

    public void setRoleInfos(List<RoleInfo> roleInfos) {
        this.roleInfos = roleInfos;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<RoleInfo> roleInfoList = this.getRoleInfos();
        if (!CollectionUtils.isEmpty(roleInfoList)) {
            for (RoleInfo roleInfo : roleInfoList) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleInfo.getId());
                authorityList.add(grantedAuthority);
            }
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return this.getPassWord();
    }

    @Override
    public String getUsername() {
        return this.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
