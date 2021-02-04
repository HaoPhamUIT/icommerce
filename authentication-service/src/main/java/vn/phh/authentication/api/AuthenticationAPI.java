package vn.phh.authentication.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.phh.authentication.config.ApiConstants;
import vn.phh.authentication.dto.UserRequest;
import vn.phh.commons.model.response.Response;


@Api(value = "Authentication api", description = "Authentication api")
public interface AuthenticationAPI {


    @ApiOperation(value = "User login", response = Response.class)
    @PostMapping(path = ApiConstants.LINE_MANAGEMENT)
    Response Login(@RequestBody UserRequest dto);


}
