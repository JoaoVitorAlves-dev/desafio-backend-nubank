package joao.alves.desafionubank.desafionubank.dto.request;

import java.time.LocalDateTime;

public record ClientesRequest(
        String nome,
        Boolean situacao,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long contatosId
) {
}
