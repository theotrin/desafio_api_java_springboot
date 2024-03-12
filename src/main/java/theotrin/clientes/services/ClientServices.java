package theotrin.clientes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import theotrin.clientes.dto.ClientDTO;
import theotrin.clientes.entities.Client;
import theotrin.clientes.repositories.ClientRepository;
import theotrin.clientes.services.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServices {

    @Autowired
    private ClientRepository repository;

    public List<ClientDTO> findAll() {
         List<Client> list = repository.findAll();

         List<ClientDTO> listDto = list.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());

         return listDto;
    }

    public ClientDTO findOne(Long id) {
        Optional<Client> optional = repository.findById(id);

        Client entity = optional.orElseThrow(() -> new EntityNotFoundException("Client not found"));

        return new ClientDTO(entity);
    }



    public ClientDTO insert(ClientDTO data) {
        Client entity = new Client();
        copyToEntity(data, entity);
        repository.save(entity);
        return new ClientDTO(entity);
    }






    private void copyToEntity(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
