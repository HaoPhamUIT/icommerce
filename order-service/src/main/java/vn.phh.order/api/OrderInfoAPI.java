package vn.phh.order.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import vn.phh.commons.model.response.Response;
import vn.phh.order.config.ApiConstants;
import vn.phh.order.dto.OrderInfoDTO;

import java.util.List;


@Api(value = "OrderInfo api", description = "OrderInfo APIs")
public interface OrderInfoAPI {

    @ApiOperation(value = "List of OrderInfo by customer", response = Response.class)
    @GetMapping(path = ApiConstants.ORDER)
    Response list();
    
    @ApiOperation(value = "Add OrderInfo by customer", response = Response.class)
    @PostMapping(path = ApiConstants.ORDER)
    Response add(@RequestBody OrderInfoDTO dto);

    @ApiOperation(value = "Update status order by customer", response = Response.class)
    @PutMapping(path = ApiConstants.ORDER)
    Response update(@RequestParam int status);

    @ApiOperation(value = "Delete OrderInfo by customer", response = Response.class)
    @DeleteMapping(path = ApiConstants.ORDER_BY_ID_END_POINT)
    Response delete(@PathVariable("id") String id);

    @ApiOperation(value = "Get OrderInfo by id", response = Response.class)
    @GetMapping(path = ApiConstants.ORDER_BY_ID_END_POINT)
    Response findById(@PathVariable("id") String id);

}
