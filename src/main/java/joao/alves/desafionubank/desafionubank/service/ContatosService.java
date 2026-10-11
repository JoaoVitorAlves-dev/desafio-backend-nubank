package joao.alves.desafionubank.desafionubank.service;

import jakarta.transaction.Transactional;
import joao.alves.desafionubank.desafionubank.dto.request.ContatosRequest;
import joao.alves.desafionubank.desafionubank.dto.response.ContatosResponse;
import joao.alves.desafionubank.desafionubank.mapper.ContatosMapper;
import joao.alves.desafionubank.desafionubank.model.Clientes;
import joao.alves.desafionubank.desafionubank.model.Contatos;
import joao.alves.desafionubank.desafionubank.repository.ClientesRepository;
import joao.alves.desafionubank.desafionubank.repository.ContatosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ContatosService {

    private final ContatosRepository contatosRepository;
    private final ClientesRepository clientesRepository;

    @Transactional
    public ContatosResponse cadastrar(ContatosRequest contatosRequest) {
        if (!clientesRepository.findById(contatosRequest.clientesId())) {
            throw new RuntimeException("Clientes não existente.");
        }
        Contatos save = contatosRepository.save(ContatosMapper.toEntity(contatosRequest));
        return ContatosMapper.toDTO(save);
    }

    public Set<Clientes> listarClientes(Long id) {
        Contatos contatos = contatosRepository.findById(id).orElseThrow(() -> new RuntimeException("Contato não encontrado."));
        return contatos.getClientes();
    }

    public Page<ContatosResponse> listar(Pageable pageable) {
        return contatosRepository.findAll(pageable).map(ContatosMapper::toDTO);
    }

    @Transactional
    public ContatosResponse atualizar(Long id, ContatosRequest contatosRequest) {
        contatosRepository.findById(id).orElseThrow(() -> new RuntimeException("Contato não existe."));
        Contatos entity = ContatosMapper.toEntity(contatosRequest);
        entity.setId(id);
        return ContatosMapper.toDTO(contatosRepository.save(entity));
    }

    @Transactional
    public void deletarContato(Long id) {
        contatosRepository.deleteById(id);
    }

}
