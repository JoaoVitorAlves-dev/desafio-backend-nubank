package joao.alves.desafionubank.desafionubank.service;

import jakarta.transaction.Transactional;
import joao.alves.desafionubank.desafionubank.dto.request.ContatosRequest;
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

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ContatosService {

    private final ContatosRepository contatosRepository;

    @Transactional
    public ContatosResponse cadastrar(ContatosRequest contatosRequest) {
        Contatos save = contatosRepository.save(ContatosMapper.toEntity(contatosRequest));
        return ContatosMapper.toDTO(save);
    }

    public List<ClientesResponse> listarClientes(Long id) {
        return buscarContato(id).getClientes().stream()
                .map(ClientesMapper::toDTO)
                .toList();
    }

    public Page<ContatosResponse> listar(Pageable pageable) {
        return contatosRepository.findAll(pageable).map(ContatosMapper::toDTO);
    }

    @Transactional
    public ContatosResponse atualizar(Long id, ContatosRequest request) {
        Contatos contato = buscarContato(id);
        contato.setTipo(request.tipo());
        contato.setConteudo(request.conteudo());
        if (request.situacao() != null) {
            contato.setSituacao(request.situacao());
        }
        return ContatosMapper.toDTO(contatosRepository.save(contato));
    }

    @Transactional
    public void deletarContato(Long id) {
        Contatos contato = buscarContato(id);
        if (!contato.getClientes().isEmpty()) {
            throw new RuntimeException("Contato possui clientes vinculados.");
        }
        contatosRepository.deleteById(id);
    }

    private Contatos buscarContato(Long id) {
        return contatosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado."));
    }

}
