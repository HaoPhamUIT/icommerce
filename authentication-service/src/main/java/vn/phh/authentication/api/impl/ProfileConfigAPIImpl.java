package vn.phh.authentication.api.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.phh.authentication.api.ProfileConfigAPI;
import vn.phh.authentication.config.ApiConstants;
import vn.phh.authentication.dto.ProfileDTO;
import vn.phh.authentication.service.ProfileService;
import vn.phh.commons.model.response.Response;


@RestController
@RequestMapping(value = ApiConstants.API_VERSION_1)
public class ProfileConfigAPIImpl implements ProfileConfigAPI {

    @Autowired
    ProfileService profileService;

    @Override
    public Response list() {
        return new Response(profileService.findAll());
    }

    @Override
    public Response add(@RequestBody ProfileDTO dto) {
        return new Response(profileService.add(dto));
    }

    @Override
    public Response update(@RequestBody ProfileDTO dto) {
        return new Response(profileService.update(dto.getId(), dto));
    }

    @Override
    public Response delete(@PathVariable("id") String id) {
        return new Response(profileService.deleteById(id));
    }

    @Override
    public Response findById(@PathVariable("id") String id) {
        return new Response(profileService.findById(id));
    }

}
