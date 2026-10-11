package joao.alves.desafionubank.desafionubank.dto.request;

import java.time.LocalDateTime;
import java.util.Set;

public record ContatosRequest(
        String tipo,
        String conteudo,
        Boolean situacao,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<Long> clientesId
) {
}
