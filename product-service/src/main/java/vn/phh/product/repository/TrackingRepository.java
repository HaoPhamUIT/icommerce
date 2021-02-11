package vn.phh.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.phh.product.model.Tracking;

import java.util.List;

/**
 * @author haoph
 *
 */
@Repository
public interface TrackingRepository extends MongoRepository<Tracking, String> {

    List<Tracking> findAllByCustomerId(String customerId);
}
