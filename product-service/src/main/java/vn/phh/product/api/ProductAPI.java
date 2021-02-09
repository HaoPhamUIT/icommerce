package vn.phh.product.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import vn.phh.commons.constants.CommonConstants;
import vn.phh.product.config.ApiConstants;
import vn.phh.product.dto.ProductDTO;
import vn.phh.commons.model.response.Response;
import vn.phh.product.dto.RequestFilterProduct;


@Api(value = "Product api", description = "Product APIs")
public interface ProductAPI {

    @ApiOperation(value = "List of Product", response = Response.class)
    @GetMapping(path = ApiConstants.PRODUCT)
    Response list();

    @ApiOperation(value = "Filter Product", response = Response.class)
    @PostMapping(path = ApiConstants.PRODUCT_FILTER)
    Response listProduct(@RequestBody RequestFilterProduct requestFilterProduct,
                         @RequestParam(name = "page", required = false, defaultValue = CommonConstants.DEFAULT_PAGE) Integer page,
                         @RequestParam(name = "size", required = false, defaultValue = CommonConstants.DEFAULT_MAX_NO_OF_ROWS) Integer size,
                         @RequestParam(name = "direction", required = false, defaultValue = CommonConstants.ASC_SORT) String direction,
                         @RequestParam(name = "attribute", required = false, defaultValue = CommonConstants.ATTRIBUTE_DEFAULT) String attribute);


    @ApiOperation(value = "Add Product", response = Response.class)
    @PostMapping(path = ApiConstants.PRODUCT)
    Response add(@RequestBody ProductDTO dto);

    @ApiOperation(value = "Update Product", response = Response.class)
    @PutMapping(path = ApiConstants.PRODUCT)
    Response update(@RequestBody ProductDTO dto);

    @ApiOperation(value = "Delete Product", response = Response.class)
    @DeleteMapping(path = ApiConstants.PRODUCT_BY_ID_END_POINT)
    Response delete(@PathVariable("id") String id);

    @ApiOperation(value = "Get Product by id", response = Response.class)
    @GetMapping(path = ApiConstants.PRODUCT_BY_ID_END_POINT)
    Response findById(@PathVariable("id") String id);

}
