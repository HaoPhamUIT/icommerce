package vn.phh.commons.jwt.service;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.phh.commons.jwt.utils.JWTCommonConstants;
import vn.phh.commons.jwt.utils.JWTConfigurationUtils;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

@Service
public class JWTTokenProvider {

    private JWTConfigurationUtils JWTConfigurationUtils;

    private RSA256 rsa256;

    @Autowired
    public JWTTokenProvider(JWTConfigurationUtils JWTConfigurationUtils, RSA256 rsa256) {
        this.JWTConfigurationUtils = JWTConfigurationUtils;
        this.rsa256 = rsa256;
    }


    /**
     * @param jwtBuilder
     * @return
     * @throws Exception
     */
    public String generateToken(JwtBuilder jwtBuilder) {
        Date now = new Date();
        PrivateKey privateKey = null;
        try {
            privateKey = rsa256.getPrivateKey(JWTConfigurationUtils.getJwtPrivateKey());
        } catch (Exception exception) {
            throw new JwtException(JWTCommonConstants.MISSING_PRIVATE_KEY);
        }
        if (JWTConfigurationUtils.getJwtExpiration() == null || JWTConfigurationUtils.getJwtExpiration() == 0) {
            return jwtBuilder
                    .setIssuedAt(now)
                    .signWith(SignatureAlgorithm.RS256, privateKey)
                    .compact();
        } else {
            Date expiryDate = new Date(now.getTime() + JWTConfigurationUtils.getJwtExpiration());
            return jwtBuilder
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.RS256, privateKey)
                    .compact();
        }
    }

    /**
     * @param token
     * @return
     * @throws Exception
     */
    public Claims claims(String token) {
        PublicKey publicKey = null;
        try {
            publicKey = rsa256.getPublicKey(JWTConfigurationUtils.getJwtPublicKey());
        } catch (Exception exception) {
            throw new JwtException(JWTCommonConstants.MISSING_PUBLIC_KEY);
        }
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(patchToken(token))
                .getBody();
    }

    /**
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(rsa256.getPublicKey(JWTConfigurationUtils.getJwtPublicKey())).
                    parseClaimsJws(patchToken(token));
            return true;
        } catch (ExpiredJwtException ex) {
            throw ex;
        } catch (Exception ex) {
            return false;
        }
    }

    private String patchToken(String token) {
        if (token.contains(JWTCommonConstants.TOKEN_PREFIX)) {
            return token.replaceAll(JWTCommonConstants.TOKEN_PREFIX, JWTCommonConstants.BLANK).trim();
        } else return null;
    }
}