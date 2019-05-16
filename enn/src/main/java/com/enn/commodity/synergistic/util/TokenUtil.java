package com.enn.commodity.synergistic.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.enn.commodity.synergistic.entity.User;

public class TokenUtil {
	
	public static String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getUserId(),user.getUsername(),user.getShowname(),user.getOrganizationId(),user.getOrganizationName())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

}
