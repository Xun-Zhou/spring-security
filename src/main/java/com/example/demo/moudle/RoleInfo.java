package com.example.demo.moudle;

import java.io.Serializable;

/**
 * 角色
 */
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

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
}
