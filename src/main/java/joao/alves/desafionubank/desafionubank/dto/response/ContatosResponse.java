package joao.alves.desafionubank.desafionubank.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

public record ContatosResponse(
        Long id,
        String tipo,
        String conteudo,
        Boolean situacao,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<Long> clientesId
) {
}
