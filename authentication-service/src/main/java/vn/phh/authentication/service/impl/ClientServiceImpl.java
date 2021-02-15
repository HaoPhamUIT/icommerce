package vn.phh.authentication.service.impl;

import org.springframework.stereotype.Service;
import vn.phh.authentication.dto.ClientDTO;
import vn.phh.authentication.service.ClientService;


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
