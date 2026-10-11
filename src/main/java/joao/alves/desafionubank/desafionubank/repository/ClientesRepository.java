package joao.alves.desafionubank.desafionubank.repository;

import joao.alves.desafionubank.desafionubank.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {
}
