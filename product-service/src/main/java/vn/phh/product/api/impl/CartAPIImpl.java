package vn.phh.cart.api.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.phh.commons.model.response.Response;
import vn.phh.product.api.CartAPI;
import vn.phh.product.config.ApiConstants;
import vn.phh.product.dto.CartDTO;
import vn.phh.product.service.CartService;

import java.util.List;


@RestController
@RequestMapping(value = ApiConstants.API_VERSION_1)
public class CartAPIImpl implements CartAPI {

    @Autowired
    CartService cartService;

    @Override
    public Response list() {
        return new Response(cartService.findAll());
    }

    @Override
    public Response add(@RequestBody CartDTO dto) {
        return new Response(cartService.add(dto));
    }

    @Override
    public Response add(List<String> dto) {
        return new Response(cartService.orderProducts(dto));
    }

    @Override
    public Response update(@RequestBody CartDTO dto) {
        return new Response(cartService.update(dto.getId(), dto));
    }

    @Override
    public Response delete(@PathVariable("id") String id) {
        return new Response(cartService.deleteById(id));
    }

    @Override
    public Response findById(@PathVariable("id") String id) {
        return new Response(cartService.findById(id));
    }

}
