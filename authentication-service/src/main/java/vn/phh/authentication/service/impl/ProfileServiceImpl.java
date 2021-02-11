package vn.phh.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.phh.authentication.converter.ProfileConverter;
import vn.phh.authentication.dto.ProfileDTO;
import vn.phh.authentication.model.Profile;
import vn.phh.authentication.repository.ProfileRepository;
import vn.phh.authentication.service.ProfileService;
import vn.phh.commons.calendar.DateTimeUtils;
import vn.phh.commons.exception.ResourceNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileConverter profileConverter;


    @Override
    public ProfileDTO add(ProfileDTO dto) {
        dto.setCreatedDate(DateTimeUtils.now());
        return profileConverter.convertToDTO(profileRepository.save(profileConverter.convertToEntity(dto)));
    }

    @Override
    public ProfileDTO update(String id, ProfileDTO dto) {
        Optional<Profile> Profile = profileRepository.findById(id);
        if (!Profile.isPresent())
            throw new ResourceNotFoundException("Profile is not exist");
        return profileConverter.convertToDTO(profileRepository.save(profileConverter.convertToEntity(dto)));
    }

    @Override
    public ProfileDTO findById(String id) {
        Optional<Profile> Profile = profileRepository.findById(id);
        if (!Profile.isPresent())
            throw new ResourceNotFoundException("Profile is not exist");
        return profileConverter.convertToDTO(Profile.get());
    }

    @Override
    public List<ProfileDTO> findAll() {
        List<ProfileDTO> ProfileDTOS = profileConverter.convertEntitiesToDTOs(profileRepository.findAll());
        return ProfileDTOS;
    }

    @Override
    public boolean deleteById(String id) {
        Optional<Profile> Profile = profileRepository.findById(id);
        if (!Profile.isPresent())
            throw new ResourceNotFoundException("Profile is not exist");
        profileRepository.deleteById(id);
        return true;
    }

}
