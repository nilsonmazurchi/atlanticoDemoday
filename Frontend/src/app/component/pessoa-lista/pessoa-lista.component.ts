import { Component, OnInit, Input } from '@angular/core';
import { PessoaService } from '../../pessoa.service';
import { Pessoa } from '../../models/pessoa';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pessoa-lista',
  templateUrl: './pessoa-lista.component.html',
  styleUrls: ['./pessoa-lista.component.scss']
})
export class PessoaListaComponent implements OnInit {
  @Input() pessoas: Pessoa[] = [];
  pessoaSelecionada: Pessoa | null = null;

  constructor(public readonly pessoaService: PessoaService, private readonly router: Router) {}

  ngOnInit() {
    this.carregarPessoas();
  }

  carregarPessoas() {
    this.pessoaService.getPessoas().subscribe((dados) => {
      this.pessoas = dados;
    });
  }

  selecionarPessoa(pessoa: Pessoa) {
    this.pessoaService.setModoEdicao(true);
    this.pessoaSelecionada = { ...pessoa }; // Faz uma cópia para evitar alterações diretas
  }

  aoSalvarPessoa() {
    this.pessoaSelecionada = null;
    this.carregarPessoas(); // Atualiza a lista após edição
  }

  excluirPessoa(id: number) {
    if (window.confirm('Tem certeza que deseja excluir esta pessoa?')) {
      this.pessoaService.deletePessoa(id).subscribe(() => {
        this.carregarPessoas();
      });
    }
  }

  voltarParaHome() {
    this.router.navigate(['/']);
  }
}
