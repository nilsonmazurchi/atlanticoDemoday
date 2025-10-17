package br.com.nilson.AppCadastroPessoas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.nilson.AppCadastroPessoas.Modelos.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByNomeAndCep(String nome, String cep);
    boolean existsByNomeAndCepAndEndereco(String nome, String cep, String endereco);
}
