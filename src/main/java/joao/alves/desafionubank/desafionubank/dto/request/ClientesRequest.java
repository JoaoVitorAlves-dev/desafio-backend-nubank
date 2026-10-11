package joao.alves.desafionubank.desafionubank.dto.request;

public record ClientesRequest(
        String nome,
        Boolean situacao,
        Long contatosId
) {
}
