package vn.phh.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.phh.order.model.OrderInfo;

import java.util.List;

/**
 * @author haoph
 *
 */
@Repository
public interface OrderInfoRepository extends MongoRepository<OrderInfo, String> {

    List<OrderInfo> findAllByCustomerId(String customerId);
}
