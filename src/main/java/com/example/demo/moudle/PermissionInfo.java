package com.example.demo.moudle;

import java.io.Serializable;
import java.util.List;

/**
 * 权限
 */
public class PermissionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String path;

    private String description;

    private String method;

    private List<RoleInfo> roleInfos;

    public List<RoleInfo> getRoleInfos() {
        return roleInfos;
    }

    public void setRoleInfos(List<RoleInfo> roleInfos) {
        this.roleInfos = roleInfos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
