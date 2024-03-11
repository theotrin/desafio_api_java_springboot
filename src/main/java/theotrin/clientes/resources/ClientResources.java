package theotrin.clientes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theotrin.clientes.entities.Client;
import theotrin.clientes.services.ClientServices;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResources {

    @Autowired
    private ClientServices service;

    @GetMapping
    public ResponseEntity<List<Client>> findAll(){
        List<Client> clientList = service.findAll();
        return ResponseEntity.ok().body(clientList);
    }
}
