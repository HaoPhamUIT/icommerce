package vn.phh.authentication.aop;

import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vn.phh.authentication.dto.ClientDTO;
import vn.phh.authentication.service.ClientService;
import vn.phh.commons.constants.CommonConstants;
import vn.phh.commons.exception.AccessDeniedException;
import vn.phh.commons.jwt.service.JWTTokenProvider;


import javax.servlet.http.HttpServletRequest;


/**
 * @author haoph
 */
@Aspect
@Component("validationAspect")
public class ValidationAspect {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private ClientService clientService;

    @Around("within(vn.phh.authentication.api.profile.*.*) && execution(public * *(..))")
    public Object validateRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        //Authentication
        authentication();
        return joinPoint.proceed(joinPoint.getArgs());
    }

    private void authentication() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(CommonConstants.AUTHORIZATION);
        if (!jwtTokenProvider.validateToken(token))
            throw new AccessDeniedException("Access Denied");
        else {
            Claims claims = jwtTokenProvider.claims(token);
            clientService.init(new ClientDTO(claims.getAudience(), claims.getSubject(), null));

        }
    }

}

