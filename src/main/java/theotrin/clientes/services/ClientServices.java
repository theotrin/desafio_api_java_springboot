package theotrin.clientes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import theotrin.clientes.entities.Client;
import theotrin.clientes.repositories.ClientRepository;

import java.util.List;

@Service
public class ClientServices {

    @Autowired
    private ClientRepository repository;

    public List<Client> findAll() {
         return repository.findAll();
    }
}
