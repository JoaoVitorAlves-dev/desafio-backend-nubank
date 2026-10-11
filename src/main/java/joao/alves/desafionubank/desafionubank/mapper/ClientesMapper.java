package joao.alves.desafionubank.desafionubank.mapper;

import joao.alves.desafionubank.desafionubank.dto.request.ClientesRequest;
import joao.alves.desafionubank.desafionubank.dto.response.ClientesResponse;
import joao.alves.desafionubank.desafionubank.model.Clientes;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientesMapper {

    public Clientes toEntity(ClientesRequest clientesRequest) {
        Clientes clientes = new Clientes();
        clientes.setNome(clientesRequest.nome());
        if (clientesRequest.situacao() != null) {
            clientes.setSituacao(clientesRequest.situacao());
        }
        return clientes;
    }

    public ClientesResponse toDTO(Clientes clientes) {
        Long contatosId = clientes.getContatos() != null ? clientes.getContatos().getId() : null;

        return new ClientesResponse(
                clientes.getId(), clientes.getNome(), clientes.getSituacao(), clientes.getCreatedAt(),
                clientes.getUpdatedAt(), contatosId
        );
    }

}
