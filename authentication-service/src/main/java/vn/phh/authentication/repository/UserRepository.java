package vn.phh.authentication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.phh.authentication.model.User;

import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findBySocialKeyAndSocialType(String key, String type);
}
