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
        clientes.setSituacao(clientesRequest.situacao());
        clientes.setCreatedAt(clientesRequest.createdAt());
        clientes.setUpdatedAt(clientesRequest.updatedAt());
        return clientes;
    }

    public ClientesResponse toDTO(Clientes clientes) {
        return new ClientesResponse(
                clientes.getId(), clientes.getNome(), clientes.getSituacao(), clientes.getCreatedAt(), clientes.getUpdatedAt(), clientes.getContatos().getId()
        );
    }

}
