package vn.phh.authentication.api.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.phh.authentication.api.AuthenticationAPI;
import vn.phh.authentication.config.ApiConstants;
import vn.phh.authentication.dto.UserRequest;
import vn.phh.authentication.service.AuthenticationService;
import vn.phh.commons.model.response.Response;


@RestController
@RequestMapping(value = ApiConstants.API_VERSION_1)
public class AuthenticationAPIImpl implements AuthenticationAPI {

    @Autowired
    AuthenticationService authenticationService;

    @Override
    public Response Login(UserRequest dto) {
        return new Response(authenticationService.login(dto));
    }
}
