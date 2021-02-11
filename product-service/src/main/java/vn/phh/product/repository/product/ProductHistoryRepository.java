package vn.phh.product.repository.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.phh.product.model.ProductHistory;

/**
 * @author haoph
 *
 */
@Repository
public interface ProductHistoryRepository extends MongoRepository<ProductHistory, String> {

}
