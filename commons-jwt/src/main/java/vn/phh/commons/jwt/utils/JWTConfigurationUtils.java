package vn.phh.commons.jwt.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTConfigurationUtils {

    @Value("${application.jwt.expiration:#{null}}")
    private Long jwtExpiration;

    @Value("${application.jwt.publicKey:#{null}}")
    private String jwtPublicKey;

    @Value("${application.jwt.privateKey:#{null}}")
    private String jwtPrivateKey;

    public Long getJwtExpiration() {
        return jwtExpiration;
    }

    public String getJwtPublicKey() {
        return jwtPublicKey;
    }

    public String getJwtPrivateKey() {
        return jwtPrivateKey;
    }
}
