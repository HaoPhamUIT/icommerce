package vn.phh.product.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.phh.product.dto.ProductDTO;
import vn.phh.product.model.Product;
import vn.phh.product.model.ProductHistory;
import vn.phh.product.service.impl.ClientServiceImpl;

import java.time.LocalDateTime;


/**
 * @author haoph
 */
@Component("ProductConverter")
public class ProductConverter extends SuperConverter<ProductDTO, Product> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientServiceImpl clientService;

    @Override
    public ProductDTO convertToDTO(Product entity) {
        return modelMapper.map(entity, ProductDTO.class);
    }

    @Override
    public Product convertToEntity(ProductDTO dto) {
        return modelMapper.map(dto, Product.class);
    }

    public ProductHistory convertToProductHistory(Product product){
       ProductHistory productHistory = modelMapper.map(product, ProductHistory.class);
       productHistory.setId(null);
       productHistory.setCreatedDate(LocalDateTime.now());
       productHistory.setCreatedBy(clientService.get().getId());
       productHistory.setProductId(product.getId());
        return productHistory;
    }

}
