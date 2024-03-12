package theotrin.clientes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import theotrin.clientes.dto.ClientDTO;
import theotrin.clientes.entities.Client;
import theotrin.clientes.services.ClientServices;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResources {

    @Autowired
    private ClientServices service;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<ClientDTO> clientList = service.findAll();
        return ResponseEntity.ok().body(clientList);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO dto){
        ClientDTO newClient = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(newClient);
    }
}
