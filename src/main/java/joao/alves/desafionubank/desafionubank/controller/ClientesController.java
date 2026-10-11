package joao.alves.desafionubank.desafionubank.controller;

import joao.alves.desafionubank.desafionubank.dto.request.ClientesRequest;
import joao.alves.desafionubank.desafionubank.dto.response.ClientesResponse;
import joao.alves.desafionubank.desafionubank.model.Contatos;
import joao.alves.desafionubank.desafionubank.service.ClientesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClientesController {

    private final ClientesService clientesService;

    @PostMapping("/clientes")
    public ResponseEntity<ClientesResponse> cadastrar(@RequestBody ClientesRequest clientesRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientesService.cadastrar(clientesRequest));
    }

    @GetMapping("/clientes")
    public ResponseEntity<Page<ClientesResponse>> listar(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(clientesService.listarClientes(pageable));
    }

    @GetMapping("/clientes/{id}/contatos")
    public ResponseEntity<Contatos> listarContatosDoCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clientesService.listarContatosDeCliente(id));
    }

    @PutMapping("/clientes/atualizar/{id}")
    public ResponseEntity<ClientesResponse> atualizarCliente(@PathVariable Long id, @RequestBody ClientesRequest clientesRequest) {
        return ResponseEntity.ok(clientesService.atualizarCliente(id, clientesRequest));
    }

    @DeleteMapping("/clientes/deletar/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clientesService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
