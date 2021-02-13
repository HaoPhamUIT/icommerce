package vn.phh.order.api.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.phh.commons.model.response.Response;
import vn.phh.order.api.OrderInfoAPI;
import vn.phh.order.config.ApiConstants;
import vn.phh.order.dto.OrderInfoDTO;
import vn.phh.order.service.OrderInfoService;


@RestController
@RequestMapping(value = ApiConstants.API_VERSION_1)
public class OrderInfoAPIImpl implements OrderInfoAPI {

    @Autowired
    OrderInfoService orderInfoService;

    @Override
    public Response list() {
        return new Response(orderInfoService.findAll());
    }

    @Override
    public Response add(@RequestBody OrderInfoDTO dto) {
        return new Response(orderInfoService.add(dto));
    }

    @Override
    public Response update(@RequestParam int status) {
        return new Response(orderInfoService.updateStatusOrder(status));
    }

    @Override
    public Response delete(@PathVariable("id") String id) {
        return new Response(orderInfoService.deleteById(id));
    }

    @Override
    public Response findById(@PathVariable("id") String id) {
        return new Response(orderInfoService.findById(id));
    }

}
