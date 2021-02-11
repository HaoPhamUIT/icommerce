package vn.phh.product.repository.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.phh.product.model.Cart;

import java.util.List;
import java.util.Optional;

/**
 * @author haoph
 *
 */
@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    Optional<Cart> findByProductIdAndCustomerId(String productId, String customerId);

    List<Cart> findAllByCustomerId(String customerId);

    List<Cart> findAllByIdInAndStatus(List<String> cartId, int status);
}
