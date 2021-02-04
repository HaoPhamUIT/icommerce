package vn.phh.authentication.service;


import vn.phh.authentication.dto.UserRequest;

public interface AuthenticationService {

   String login(UserRequest userRequest);

}
