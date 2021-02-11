package vn.phh.product.repository.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.phh.product.model.Product;

/**
 * @author haoph
 *
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
