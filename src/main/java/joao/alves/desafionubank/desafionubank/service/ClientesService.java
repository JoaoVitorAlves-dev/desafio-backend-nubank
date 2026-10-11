package joao.alves.desafionubank.desafionubank.service;

import jakarta.transaction.Transactional;
import joao.alves.desafionubank.desafionubank.dto.request.ClientesRequest;
import joao.alves.desafionubank.desafionubank.dto.response.ClientesResponse;
import joao.alves.desafionubank.desafionubank.dto.response.ContatosResponse;
import joao.alves.desafionubank.desafionubank.mapper.ClientesMapper;
import joao.alves.desafionubank.desafionubank.mapper.ContatosMapper;
import joao.alves.desafionubank.desafionubank.model.Clientes;
import joao.alves.desafionubank.desafionubank.model.Contatos;
import joao.alves.desafionubank.desafionubank.repository.ClientesRepository;
import joao.alves.desafionubank.desafionubank.repository.ContatosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientesService {

    private final ClientesRepository clientesRepository;
    private final ContatosRepository contatosRepository;

    @Transactional
    public ClientesResponse cadastrar(ClientesRequest clientesRequest) {
        if (clientesRepository.existsByNome(clientesRequest.nome())) {
            throw new RuntimeException("Nome já existe");
        }
        Clientes entity = ClientesMapper.toEntity(clientesRequest);
        entity.setContatos(buscarContato(clientesRequest.contatosId()));
        Clientes save = clientesRepository.save(entity);
        return ClientesMapper.toDTO(save);
    }

    public Page<ClientesResponse> listarClientes(Pageable pageable) {
        return clientesRepository.findAll(pageable).map(ClientesMapper::toDTO);
    }

    public ContatosResponse listarContatosDeCliente(Long id) {
        Contatos contatos = buscarCliente(id).getContatos();
        if (contatos == null) {
            throw new RuntimeException("Cliente não possui contato.");
        }
        return  ContatosMapper.toDTO(contatos);
    }

    @Transactional
    public ClientesResponse atualizarCliente(Long id, ClientesRequest clientesRequest) {
        Clientes cliente = buscarCliente(id);
        if (clientesRepository.existsByNomeAndIdNot(clientesRequest.nome(), id)) {
            throw new RuntimeException("Nome já existe.");
        }
        cliente.setNome(clientesRequest.nome());
        if (clientesRequest.situacao() != null) {
            cliente.setSituacao(clientesRequest.situacao());
        }
        cliente.setContatos(buscarContato(clientesRequest.contatosId()));
        return ClientesMapper.toDTO(clientesRepository.save(cliente));
    }

    @Transactional
    public void deletar(Long id) {
        if (!clientesRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado.");
        }
        clientesRepository.deleteById(id);
    }

    private Clientes buscarCliente(Long id) {
        return clientesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
    }

    private Contatos buscarContato(Long id) {
        return contatosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado"));
    }

}
