package joao.alves.desafionubank.desafionubank.repository;

import joao.alves.desafionubank.desafionubank.model.Contatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatosRepository extends JpaRepository<Contatos, Long> {
}
