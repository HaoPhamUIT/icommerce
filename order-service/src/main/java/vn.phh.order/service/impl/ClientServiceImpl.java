package vn.phh.order.service.impl;

import org.springframework.stereotype.Service;
import vn.phh.order.dto.ClientDTO;
import vn.phh.order.service.ClientService;

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
