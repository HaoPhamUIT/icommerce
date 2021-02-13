package vn.phh.authentication.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.phh.authentication.component.JWTComponent;
import vn.phh.authentication.converter.UserConverter;
import vn.phh.authentication.dto.UserRequest;
import vn.phh.authentication.model.User;
import vn.phh.authentication.repository.UserRepository;
import vn.phh.authentication.service.AuthenticationService;
import vn.phh.authentication.utils.Constants;
import vn.phh.commons.exception.IllegalServiceException;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

   @Autowired
   UserRepository userRepository;
   
   @Autowired
   UserConverter userConverter;

   @Autowired
   private JWTComponent jwtComponent;

   @Override
   public String login(UserRequest userRequest) {
      if(!StringUtils.isEmpty(userRequest.getSocialNetworkKey()) && !StringUtils.isEmpty((userRequest.getSocialNetworkType()))){
         Optional<User> user = userRepository.findBySocialNetworkKeyAndSocialNetworkType(userRequest.getSocialNetworkKey(), userRequest.getSocialNetworkType());
         if(!user.isPresent()){
             return jwtComponent.createToken(userRepository.save(userConverter.convertToEntity(userRequest)));
         }else return jwtComponent.createToken(user.get());
      }else throw new IllegalServiceException(Constants.ERROR_SOCIAL_KEY_MISSING);
   }
}
