package vn.phh.authentication.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import vn.phh.authentication.config.ApiConstants;
import vn.phh.authentication.dto.ProfileDTO;
import vn.phh.commons.model.response.Response;


@Api(value = "Profile Config", description = "Profile Config APIs")
public interface ProfileConfigAPI {

    @ApiOperation(value = "List of Profile config", response = Response.class)
    @GetMapping(path = ApiConstants.PROFILE)
    Response list();

    @ApiOperation(value = "Add Profile config", response = Response.class)
    @PostMapping(path = ApiConstants.PROFILE)
    Response add(@RequestBody ProfileDTO dto);

    @ApiOperation(value = "Update Profile config", response = Response.class)
    @PutMapping(path = ApiConstants.PROFILE)
    Response update(@RequestBody ProfileDTO dto);

    @ApiOperation(value = "Delete Profile config", response = Response.class)
    @DeleteMapping(path = ApiConstants.PROFILE_BY_ID_END_POINT)
    Response delete(@PathVariable("id") String id);

    @ApiOperation(value = "Get Profile config by id", response = Response.class)
    @GetMapping(path = ApiConstants.PROFILE_BY_ID_END_POINT)
    Response findById(@PathVariable("id") String id);

}
