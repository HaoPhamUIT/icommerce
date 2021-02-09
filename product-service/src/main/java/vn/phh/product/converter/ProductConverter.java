package vn.phh.product.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.phh.product.dto.ProductDTO;
import vn.phh.product.model.Product;


/**
 * @author haoph
 */
@Component("ProductConverter")
public class ProductConverter extends SuperConverter<ProductDTO, Product> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO convertToDTO(Product entity) {
        return modelMapper.map(entity, ProductDTO.class);
    }

    @Override
    public Product convertToEntity(ProductDTO dto) {
        return modelMapper.map(dto, Product.class);
    }


}
