import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pessoa } from 'src/app/models/pessoa';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  // URL do backend ou arquivo JSON simulado
  private readonly apiUrl = 'http://localhost:8080/pessoas';

  constructor(private readonly http: HttpClient) { }

  // Método para cadastrar uma pessoa, enviando os dados para o backend
  cadastrarPessoa(pessoa: Pessoa): Observable<Pessoa> {
    return this.http.post<Pessoa>(this.apiUrl, pessoa);
      }

  // Método para obter as pessoas cadastradas
  getPessoas(): Observable<Pessoa[]> {
    return this.http.get<Pessoa[]>(this.apiUrl);
  }

  // Obtém uma pessoa pelo ID
  getPessoaById(id: number): Observable<Pessoa> {
    return this.http.get<Pessoa>(`${this.apiUrl}/${id}`);
  }

  // Buscar endereço por CEP usando a API ViaCEP
  buscarEnderecoPorCep(cep: string): Observable<any> {
    return this.http.get(`https://viacep.com.br/ws/${cep}/json/`);
  }

  // Método para verificar se já existe uma pessoa com o mesmo conjunto de dados
  verificarPessoaExistente(pessoa: Pessoa): Observable<Pessoa[]> {
    // Aqui você pode comparar com os dados no arquivo JSON ou backend
    return this.http.get<Pessoa[]>(this.apiUrl);
  }

  // Método para atualizar os dados de uma pessoa existente
  updatePessoa(id: number, pessoa: Pessoa): Observable<Pessoa> {
    return this.http.put<Pessoa>(`${this.apiUrl}/${id}`, pessoa);
  }

  // Método para excluir uma pessoa
  deletePessoa(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  private _modoEdicao = false;
  setModoEdicao(valor: boolean) {
    this._modoEdicao = valor;
  }

  getModoEdicao(): boolean {
    return this._modoEdicao;
  }
}
