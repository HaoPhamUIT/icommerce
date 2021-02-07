package vn.phh.authentication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.phh.authentication.model.Profile;


import java.util.List;

/**
 * @author haoph
 *
 */
@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

}
