package com.example.demo.component;

import com.alibaba.fastjson.JSON;
import com.example.demo.constant.Constants;
import com.example.demo.moudle.AuthUserDetails;
import com.example.demo.moudle.RoleInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenComponent {

    public String createAccessJwtToken(AuthUserDetails authUserDetails) {
        Claims claims = Jwts.claims().setSubject(authUserDetails.getUsername());
        List<String> roleIds = authUserDetails.getRoleInfos().stream().map(RoleInfo::getId).collect(Collectors.toList());
        claims.put(Constants.AUTHORITIES, JSON.toJSONString(roleIds));
        LocalDateTime currentTime = LocalDateTime.now();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(Constants.ISSUER)
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime
                        .plusMinutes(Constants.TOKEN_EXPIRATION_TIME)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, Constants.SECURITY_KEY)
                .compact();
    }
}
