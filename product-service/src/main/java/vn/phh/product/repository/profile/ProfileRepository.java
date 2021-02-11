package vn.phh.product.repository.profile;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.phh.product.model.Profile;

/**
 * @author haoph
 *
 */
@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {

}
