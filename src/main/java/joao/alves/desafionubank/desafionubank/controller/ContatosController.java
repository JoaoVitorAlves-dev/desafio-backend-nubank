package joao.alves.desafionubank.desafionubank.controller;

import joao.alves.desafionubank.desafionubank.dto.request.ContatosRequest;
import joao.alves.desafionubank.desafionubank.dto.response.ContatosResponse;
import joao.alves.desafionubank.desafionubank.model.Clientes;
import joao.alves.desafionubank.desafionubank.service.ContatosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ContatosController {

    private final ContatosService contatosService;

    @PostMapping("/contatos")
    public ResponseEntity<ContatosResponse> cadastrar(@RequestBody ContatosRequest contatosRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatosService.cadastrar(contatosRequest));
    }

    @GetMapping("/contatos")
    public ResponseEntity<Page<ContatosResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(contatosService.listar(pageable));
    }

    @GetMapping("/contatos/{id}/clientes")
    public ResponseEntity<Set<Clientes>> listarClientesDoContato(@PathVariable Long id) {
        return ResponseEntity.ok(contatosService.listarClientes(id));
    }

    @PutMapping("/contatos/atualizar/{id}")
    public ResponseEntity<ContatosResponse> atualizarContato(@PathVariable Long id, @RequestBody ContatosRequest contatosRequest) {
        return ResponseEntity.ok(contatosService.atualizar(id, contatosRequest));
    }

    @DeleteMapping("/contatos/deletar/{id}")
    public ResponseEntity<Void> deletarContato(@PathVariable Long id) {
        contatosService.deletarContato(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
