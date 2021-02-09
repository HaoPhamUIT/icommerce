package vn.phh.product.api.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.phh.product.api.ProductAPI;
import vn.phh.product.config.ApiConstants;
import vn.phh.product.dto.ProductDTO;
import vn.phh.product.dto.RequestFilterProduct;
import vn.phh.product.service.ProductService;
import vn.phh.commons.model.response.Response;


@RestController
@RequestMapping(value = ApiConstants.API_VERSION_1)
public class ProductAPIImpl implements ProductAPI {

    @Autowired
    ProductService productService;

    @Override
    public Response list() {
        return new Response(productService.findAll());
    }

    @Override
    public Response listProduct(RequestFilterProduct requestFilterProduct, Integer page, Integer size, String direction, String attribute) {
        return new Response(productService.filterProduct(requestFilterProduct, page, size, direction, attribute));
    }

    @Override
    public Response add(@RequestBody ProductDTO dto) {
        return new Response(productService.add(dto));
    }

    @Override
    public Response update(@RequestBody ProductDTO dto) {
        return new Response(productService.update(dto.getId(), dto));
    }

    @Override
    public Response delete(@PathVariable("id") String id) {
        return new Response(productService.deleteById(id));
    }

    @Override
    public Response findById(@PathVariable("id") String id) {
        return new Response(productService.findById(id));
    }

}
