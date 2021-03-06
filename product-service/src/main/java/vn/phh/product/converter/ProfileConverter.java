package vn.phh.product.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.phh.product.dto.ProfileDTO;
import vn.phh.product.model.Profile;


/**
 * @author haoph
 */
@Component("profileConverter")
public class ProfileConverter extends SuperConverter<ProfileDTO, Profile> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProfileDTO convertToDTO(Profile entity) {
        return modelMapper.map(entity, ProfileDTO.class);
    }

    @Override
    public Profile convertToEntity(ProfileDTO dto) {
        return modelMapper.map(dto,Profile.class);
    }


}
