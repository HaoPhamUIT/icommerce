package vn.phh.product.service;



import org.springframework.data.domain.Page;
import vn.phh.product.dto.ProductDTO;
import vn.phh.product.dto.RequestFilterProduct;

import java.util.List;

public interface ProductService {

   ProductDTO add(ProductDTO dto);

   ProductDTO update(String id, ProductDTO dto);

   ProductDTO findById(String id);

    List<ProductDTO> findAll();

    boolean deleteById(String id);

    Page<ProductDTO> filterProduct(RequestFilterProduct requestFilterProduct, int page, int size, String direction, String attribute);


}
