package br.com.nilson.AppCadastroPessoas.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.nilson.AppCadastroPessoas.Modelos.*;
import br.com.nilson.AppCadastroPessoas.Repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    /**
     * Cadastra uma nova pessoa ou atualiza os dados caso o ID já exista.
     * Se a pessoa já existir, seus dados são atualizados sem alterar o ID.
     */
    public Pessoa cadastrar(Pessoa pessoa) {
        if (pessoa.getId() != null && repository.existsById(pessoa.getId())) {
            Pessoa existente = repository.findById(pessoa.getId()).orElseThrow();
            BeanUtils.copyProperties(pessoa, existente, "id"); // Copia dados sem alterar o ID
            return repository.save(existente);
        } else {
            return repository.saveAndFlush(pessoa);
        }
    }

    /**
     * Retorna a lista de todas as pessoas cadastradas no banco de dados.
     */
    public List<Pessoa> listar() {
        return repository.findAll();
    }

    /**
     * Busca uma pessoa pelo ID.
     * Retorna um Optional contendo a pessoa, caso encontrada.
     */
    public Optional<Pessoa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    /**
     * Atualiza os dados de uma pessoa com base no ID fornecido.
     * Lança uma exceção caso a pessoa não seja encontrada.
     */
    public Pessoa atualizar(Long id, Pessoa pessoaAtualizada) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.setNome(pessoaAtualizada.getNome());
        pessoa.setCep(pessoaAtualizada.getCep());
        pessoa.setEndereco(pessoaAtualizada.getEndereco());
        pessoa.setNumero(pessoaAtualizada.getNumero());
        pessoa.setComplemento(pessoaAtualizada.getComplemento());
        pessoa.setBairro(pessoaAtualizada.getBairro());
        pessoa.setCidade(pessoaAtualizada.getCidade());
        pessoa.setUf(pessoaAtualizada.getUf());
        return repository.save(pessoa);
    }

    /**
     * Deleta uma pessoa do banco de dados com base no ID fornecido.
     */
    public void deletar(Long id) {
        repository.deleteById(id);
    }
    
    /**
     * Verifica se uma pessoa já existe no banco de dados com base nos dados informados.
     */
    public boolean existePessoa(String nome, String cep, String endereco, String numero) {
        return repository.existsByNomeAndCepAndEndereco(nome, cep, endereco);
    }
}