package joao.alves.desafionubank.desafionubank.service;

import jakarta.transaction.Transactional;
import joao.alves.desafionubank.desafionubank.dto.request.ClientesRequest;
import joao.alves.desafionubank.desafionubank.dto.response.ClientesResponse;
import joao.alves.desafionubank.desafionubank.mapper.ClientesMapper;
import joao.alves.desafionubank.desafionubank.model.Clientes;
import joao.alves.desafionubank.desafionubank.model.Contatos;
import joao.alves.desafionubank.desafionubank.repository.ClientesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientesService {

    private final ClientesRepository clientesRepository;

    @Transactional
    public ClientesResponse cadastrar(ClientesRequest clientesRequest) {
        if (clientesRepository.existsByNome(clientesRequest.nome())) {
            throw new RuntimeException("Nome já existe");
        }
        Clientes save = clientesRepository.save(ClientesMapper.toEntity(clientesRequest));
        return ClientesMapper.toDTO(save);
    }

    public Page<ClientesResponse> listarClientes(Pageable pageable) {
        return clientesRepository.findAll(pageable).map(ClientesMapper::toDTO);
    }

    public Contatos listarContatosDeCliente(Long id) {
        Clientes clientes = clientesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não possui contatos."));
        return clientes.getContatos();
    }

    @Transactional
    public ClientesResponse atualizarCliente(Long id, ClientesRequest clientesRequest) {
        if (clientesRepository.existsByNome(clientesRequest.nome())) {
            throw new RuntimeException("Nome já existe.");
        }
        clientesRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        Clientes entity = ClientesMapper.toEntity(clientesRequest);
        entity.setId(id);
        return ClientesMapper.toDTO(clientesRepository.save(entity));
    }

    @Transactional
    public void deletar(Long id) {
        clientesRepository.deleteById(id);
    }


}
