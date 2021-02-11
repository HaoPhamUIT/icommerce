package vn.phh.product.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import vn.phh.commons.model.response.Response;
import vn.phh.product.config.ApiConstants;
import vn.phh.product.dto.CartDTO;

import java.util.List;


@Api(value = "Cart api", description = "Cart APIs")
public interface CartAPI {

    @ApiOperation(value = "List of Cart by customer", response = Response.class)
    @GetMapping(path = ApiConstants.CART)
    Response list();
    
    @ApiOperation(value = "Add Cart by customer", response = Response.class)
    @PostMapping(path = ApiConstants.CART)
    Response add(@RequestBody CartDTO dto);

    @ApiOperation(value = "Order products by customer", response = Response.class)
    @PostMapping(path = ApiConstants.ORDER)
    Response add(@RequestBody List<String> dto);

    @ApiOperation(value = "Update Cart by customer", response = Response.class)
    @PutMapping(path = ApiConstants.CART)
    Response update(@RequestBody CartDTO dto);

    @ApiOperation(value = "Delete Cart by customer", response = Response.class)
    @DeleteMapping(path = ApiConstants.CART_BY_ID_END_POINT)
    Response delete(@PathVariable("id") String id);

    @ApiOperation(value = "Get Cart by id", response = Response.class)
    @GetMapping(path = ApiConstants.CART_BY_ID_END_POINT)
    Response findById(@PathVariable("id") String id);

}
