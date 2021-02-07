package vn.phh.authentication.service;



import vn.phh.authentication.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {

   ProfileDTO add(ProfileDTO dto);

   ProfileDTO update(String id,ProfileDTO dto);

   ProfileDTO findById(String id);

    List<ProfileDTO> findAll();

    boolean deleteById(String id);



}
