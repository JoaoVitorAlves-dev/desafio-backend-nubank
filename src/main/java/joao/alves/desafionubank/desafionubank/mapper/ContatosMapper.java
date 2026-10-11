package joao.alves.desafionubank.desafionubank.mapper;

import joao.alves.desafionubank.desafionubank.dto.request.ContatosRequest;
import joao.alves.desafionubank.desafionubank.dto.response.ContatosResponse;
import joao.alves.desafionubank.desafionubank.model.Clientes;
import joao.alves.desafionubank.desafionubank.model.Contatos;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class ContatosMapper {

    public Contatos toEntity(ContatosRequest contatosRequest) {
        Contatos contatos = new Contatos();
        contatos.setTipo(contatosRequest.tipo());
        contatos.setConteudo(contatosRequest.conteudo());
        contatos.setSituacao(contatosRequest.situacao());
        contatos.setCreatedAt(contatosRequest.createdAt());
        contatos.setUpdatedAt(contatosRequest.updatedAt());
        return contatos;
    }

    public ContatosResponse toDTO(Contatos contatos) {
        Set<Long> clientesId = contatos.getClientes().stream()
                .map(Clientes::getId)
                .collect(Collectors.toSet());
        return new ContatosResponse(
                contatos.getId(), contatos.getTipo(), contatos.getConteudo(), contatos.getSituacao(),
                contatos.getCreatedAt(), contatos.getUpdatedAt(), clientesId
        );
    }

}
