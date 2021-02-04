package vn.phh.authentication.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import vn.phh.authentication.model.User;
import vn.phh.commons.constants.CommonConstants;
import vn.phh.commons.jwt.service.JWTTokenProvider;
import vn.phh.commons.jwt.service.RSA256;
import vn.phh.commons.jwt.utils.JWTConfigurationUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Primary
public class JWTComponent extends JWTTokenProvider {

    @Autowired
    public JWTComponent(JWTConfigurationUtils JWTConfigurationUtils, RSA256 rsa256) {
        super(JWTConfigurationUtils, rsa256);
    }

    public String getNewToken(String token) {
        Claims oldToken = this.claims(token);
        Map<String, Object> claims = new HashMap<>();
        claims.put(CommonConstants.DEVICE_ID, oldToken.get(CommonConstants.DEVICE_ID));
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(oldToken.getSubject())
                .setAudience(oldToken.getAudience())
                .setIssuer(oldToken.getIssuer())
                .addClaims(claims);
        return this.generateToken(jwtBuilder);
    }

    public String createToken(User userEntity) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CommonConstants.DEVICE_ID, userEntity.getDeviceId());
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(userEntity.getId())
                .setAudience(userEntity.getId())
                .setIssuer(userEntity.getUsername())
                .addClaims(claims);
        return this.generateToken(jwtBuilder);
    }

}
