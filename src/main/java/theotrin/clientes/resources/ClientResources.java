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
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> find(@PathVariable Long id) {
        ClientDTO clientFounded = service.findOne(id);

        return ResponseEntity.ok().body(clientFounded);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO dto){
        ClientDTO newClient = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(newClient);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update( @PathVariable Long id, @RequestBody ClientDTO dto){
        ClientDTO newClient = service.update(id, dto);

        return ResponseEntity.ok().body(newClient);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
