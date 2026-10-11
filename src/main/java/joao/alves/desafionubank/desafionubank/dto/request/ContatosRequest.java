package joao.alves.desafionubank.desafionubank.dto.request;

import java.util.Set;

public record ContatosRequest(
        String tipo,
        String conteudo,
        Boolean situacao
) {
}
