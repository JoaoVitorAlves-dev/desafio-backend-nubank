package joao.alves.desafionubank.desafionubank.dto.response;

import java.time.LocalDateTime;

public record ClientesResponse(
        Long id,
        String nome,
        Boolean situacao,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long contatosId
) {
}
