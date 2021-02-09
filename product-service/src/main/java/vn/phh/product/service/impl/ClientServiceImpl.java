package vn.phh.product.service.impl;

import org.springframework.stereotype.Service;
import vn.phh.product.dto.ClientDTO;
import vn.phh.product.service.ClientService;

@Service
public class ClientServiceImpl extends ClientService<ClientDTO> {

    @Override
    public void init(ClientDTO clientDTO) {
        this.client = clientDTO;
    }

    @Override
    public ClientDTO get() {
        return this.client;
    }
}
