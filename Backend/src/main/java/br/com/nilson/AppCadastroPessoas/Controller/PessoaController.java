package br.com.nilson.AppCadastroPessoas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.nilson.AppCadastroPessoas.Modelos.*;
import br.com.nilson.AppCadastroPessoas.Service.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    /**
     * Endpoint para cadastrar uma nova pessoa.
     * Retorna a pessoa cadastrada com status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = service.cadastrar(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    /**
     * Endpoint para listar todas as pessoas cadastradas.
     * Retorna uma lista de pessoas com status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    /**
     * Endpoint para buscar uma pessoa pelo ID.
     * Retorna a pessoa encontrada ou status 404 (Not Found) se não existir.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint para atualizar os dados de uma pessoa pelo ID.
     * Retorna a pessoa atualizada com status 200 (OK).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(service.atualizar(id, pessoa));
    }

    /**
     * Endpoint para deletar uma pessoa pelo ID.
     * Retorna status 204 (No Content) caso a exclusão seja bem-sucedida.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Endpoint para verificar se uma pessoa já existe no banco de dados.
     * Retorna true se a pessoa existir, false caso contrário.
     */
    @GetMapping("/existe")
    public ResponseEntity<Boolean> verificarPessoaExistente(
            @RequestParam String nome,
            @RequestParam String cep,
            @RequestParam String endereco,
            @RequestParam String numero
            ) {
        
        boolean existe = service.existePessoa(nome, cep, endereco, numero);
        return ResponseEntity.ok(existe);
    }
}