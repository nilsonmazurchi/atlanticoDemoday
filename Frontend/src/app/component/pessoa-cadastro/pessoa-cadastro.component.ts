import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Pessoa } from 'src/app/models/pessoa';
import { PessoaService } from '../../pessoa.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-pessoa-cadastro',
  templateUrl: './pessoa-cadastro.component.html',
  styleUrls: ['./pessoa-cadastro.component.scss']
})
export class PessoaCadastroComponent {

  // Objeto pessoa inicializado com valores padrão
  // Recebe a pessoa a ser editada
  @Input() pessoa: Pessoa = { // Inicializa um objeto vazio para evitar null
    nome: '',
    endereco: '',
    numero: '',
    complemento: '',
    bairro: '',
    cep: '',
    cidade: '',
    uf: ''
  };
  @Output() salvo = new EventEmitter<void>(); // Notifica o componente pai após salvar

  // Variável de controle para exibição de erros
  submitted = false;

  // Construtor para injeção de dependências
  constructor(private readonly pessoaService: PessoaService, private readonly router: Router) {}

  /**
   * Método para cadastrar uma nova pessoa.
   * Verifica se o formulário é válido antes de enviar os dados.
   * Após o cadastro, limpa o formulário e reseta os erros.
   */
  cadastrarPessoa(form: NgForm) {
    this.submitted = true; // Ativa a exibição das mensagens de erro

    if (form.invalid) {
      alert('Por favor, preencha todos os campos corretamente.');
      return;
    }

    if (this.pessoa?.id) {
    this.pessoaService.cadastrarPessoa(this.pessoa).subscribe({
      next: () => {
        alert('Pessoa cadastrada com sucesso!');
        this.salvo.emit(); // Notifica o componente pai que a ação foi concluída
      },
      error: (erro) => {
        alert('Erro ao atualizar pessoa: ' + erro.error.message);
        }
      });
    } else {
        // Se não tem ID, é um novo cadastro
      this.pessoaService.cadastrarPessoa(this.pessoa!).subscribe({
        next: () => {
          alert('Pessoa cadastrada com sucesso!');
          // Reseta os campos após o cadastro
          this.pessoa = { nome: '', cep: '', endereco: '', numero: '', complemento: '', bairro: '', cidade: '', uf: '' };
          this.submitted = false;
          form.resetForm();
          this.salvo.emit(); // Notifica o componente pai
        },
        error: (erro) => {
          alert('Erro ao cadastrar pessoa: ' + erro.error.message);
        }

      });
    }
    this.pessoaService.setModoEdicao(false); // Desativa o modo de edição
  }

  /**
   * Método para buscar o endereço automaticamente com base no CEP informado.
   * Valida se o CEP possui 8 dígitos antes de fazer a requisição ao serviço.
   */
  buscarEnderecoPorCep() {
    if (!this.pessoa?.cep) return;

    const cepFormatado = this.pessoa.cep.replace(/\D/g, ''); // Remove caracteres não numéricos

    if (cepFormatado.length === 8) {
      this.pessoaService.buscarEnderecoPorCep(cepFormatado).subscribe({
        next: (dados) => {
          if (dados.logradouro) {
            // Atualiza os campos do endereço
            this.pessoa!.endereco = dados.logradouro;
            this.pessoa!.bairro = dados.bairro;
            this.pessoa!.cidade = dados.localidade;
            this.pessoa!.uf = dados.uf;
          } else {
            alert('Endereço não encontrado para este CEP.');
          }
        },
        error: () => {
          alert('Erro ao buscar dados do CEP.');
        }
      });
    } else if (cepFormatado.length > 0) {
      alert('CEP inválido. O CEP deve ter 8 dígitos.');
    }
  }

  /**
   * Método chamado automaticamente ao digitar no campo de CEP.
   * Faz a busca do endereço quando o CEP atinge 8 dígitos.
   */
  onCepInput() {
    if (!this.pessoa?.cep) return;

    const cepFormatado = this.pessoa.cep.replace(/\D/g, ''); // Remove caracteres não numéricos

    if (cepFormatado.length === 8) {
      this.pessoaService.buscarEnderecoPorCep(cepFormatado).subscribe({
        next: (dados) => {
          if (dados.logradouro) {
            this.pessoa!.endereco = dados.logradouro;
            this.pessoa!.bairro = dados.bairro;
            this.pessoa!.cidade = dados.localidade;
            this.pessoa!.uf = dados.uf;
          } else {
            alert('Endereço não encontrado para este CEP.');
          }
        },
        error: () => {
          alert('Erro ao buscar dados do CEP.');
        }
      });
    }
  }

  /**
   * Redireciona o usuário para a página inicial.
   */
  voltarParaHome() {
    this.router.navigate(['/']); // Redireciona para a raiz
  }

}
