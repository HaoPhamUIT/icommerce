package vn.phh.authentication.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.phh.authentication.dto.UserRequest;
import vn.phh.authentication.model.User;

import java.time.LocalDateTime;


/**
 * @author haoph
 */
@Component("userConverter")
public class UserConverter extends SuperConverter<UserRequest, User> {

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserRequest convertToDTO(User entity) {
        return modelMapper.map(entity, UserRequest.class);
    }

    @Override
    public User convertToEntity(UserRequest dto) {
        User user = modelMapper.map(dto, User.class);
        user.setCreatedDate(LocalDateTime.now());
        return user;
    }
}
