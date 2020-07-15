package com.example.demo.service;

import com.example.demo.moudle.PermissionInfo;
import com.example.demo.moudle.RoleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限service
 */
public class PermissionServiceImpl {

    /**
     * 权限对应角色
     */
    public static final List<PermissionInfo> PERMISSIONS = new ArrayList<>();

    static {
        PermissionInfo permissionInfo1 = new PermissionInfo();
        permissionInfo1.setId("1");
        permissionInfo1.setPath("/users");
        permissionInfo1.setMethod("GET");
        List<RoleInfo> roleInfos1 = new ArrayList<>();
        RoleInfo roleInfo1 = new RoleInfo();
        roleInfo1.setId("1");
        roleInfo1.setName("role1");
        roleInfos1.add(roleInfo1);
        permissionInfo1.setRoleInfos(roleInfos1);

        PermissionInfo permissionInfo2 = new PermissionInfo();
        permissionInfo2.setId("2");
        permissionInfo2.setPath("/users");
        permissionInfo2.setMethod("POST");
        List<RoleInfo> roleInfos2 = new ArrayList<>();
        RoleInfo roleInfo2 = new RoleInfo();
        roleInfo2.setId("2");
        roleInfo2.setName("role2");
        roleInfos2.add(roleInfo2);
        permissionInfo2.setRoleInfos(roleInfos2);

        PermissionInfo permissionInfo3 = new PermissionInfo();
        permissionInfo3.setId("2");
        permissionInfo3.setPath("/users");
        permissionInfo3.setMethod("POST");
        List<RoleInfo> roleInfos3 = new ArrayList<>();
        RoleInfo roleInfo31 = new RoleInfo();
        roleInfo31.setId("1");
        roleInfo31.setName("role1");
        RoleInfo roleInfo32 = new RoleInfo();
        roleInfo32.setId("2");
        roleInfo32.setName("role2");
        roleInfos3.add(roleInfo31);
        roleInfos3.add(roleInfo32);
        permissionInfo3.setRoleInfos(roleInfos3);

        PERMISSIONS.add(permissionInfo1);
        PERMISSIONS.add(permissionInfo2);
        PERMISSIONS.add(permissionInfo3);
    }
}
