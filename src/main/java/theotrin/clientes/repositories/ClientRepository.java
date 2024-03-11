package theotrin.clientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import theotrin.clientes.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
