package theotrin.clientes.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import theotrin.clientes.dto.ClientDTO;
import theotrin.clientes.entities.Client;
import theotrin.clientes.repositories.ClientRepository;
import theotrin.clientes.services.exceptions.ResourceNotFoundException;

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

        Client entity = optional.orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        return new ClientDTO(entity);
    }



    public ClientDTO insert(ClientDTO data) {
        Client entity = new Client();
        copyToEntity(data, entity);
        repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        try{
            Client entity = repository.getOne(id);
            copyToEntity(dto, entity);
            repository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found");
        }
    }

    private void copyToEntity(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

    public void delete(Long id) {
        try{
            if (!repository.existsById(id)) {
                throw new ResourceNotFoundException("Id not found");
            }
            repository.deleteById(id);

        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found");
        }
    }
}
