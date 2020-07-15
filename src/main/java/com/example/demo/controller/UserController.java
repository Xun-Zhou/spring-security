package com.example.demo.controller;

import com.example.demo.component.TokenComponent;
import com.example.demo.moudle.AuthUserDetails;
import com.example.demo.moudle.RoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private TokenComponent tokenComponent;

    @GetMapping
    public String getUsers() {
        return "hello world";
    }

    @PostMapping
    public String addUsers() {
        return "hello world";
    }

    @GetMapping("/token")
    public String getUsersToken(String userId) {
        AuthUserDetails userDetails = new AuthUserDetails();
        if ("1".equals(userId)) {
            userDetails.setUserId("1");
            userDetails.setUserName("admin1");
            List<RoleInfo> roleInfos = new ArrayList<>();
            RoleInfo roleInfo = new RoleInfo();
            roleInfo.setId("1");
            roleInfo.setName("role1");
            roleInfos.add(roleInfo);
            userDetails.setRoleInfos(roleInfos);
        } else if ("2".equals(userId)) {
            userDetails.setUserId("2");
            userDetails.setUserName("admin2");
            List<RoleInfo> roleInfos = new ArrayList<>();
            RoleInfo roleInfo = new RoleInfo();
            roleInfo.setId("2");
            roleInfo.setName("role2");
            roleInfos.add(roleInfo);
            userDetails.setRoleInfos(roleInfos);
        } else {
            userDetails.setUserId("3");
            userDetails.setUserName("admin3");
            List<RoleInfo> roleInfos = new ArrayList<>();
            RoleInfo roleInfo1 = new RoleInfo();
            roleInfo1.setId("1");
            roleInfo1.setName("role1");
            RoleInfo roleInfo2 = new RoleInfo();
            roleInfo2.setId("2");
            roleInfo2.setName("role2");
            roleInfos.add(roleInfo1);
            roleInfos.add(roleInfo2);
            userDetails.setRoleInfos(roleInfos);
        }
        //创建token
        return tokenComponent.createAccessJwtToken(userDetails);
    }
}
