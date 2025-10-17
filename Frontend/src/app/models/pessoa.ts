// Definição da interface Pessoa, que representa um modelo de dados para cadastro de pessoas.
export interface Pessoa {
  id?: number;          // Identificador único da pessoa
  nome: string;        // Nome completo da pessoa
  endereco: string;    // Nome da rua ou avenida do endereço
  numero: string;      // Número do endereço
  complemento: string; // Informações adicionais sobre o endereço (ex: apto, bloco)
  bairro: string;      // Bairro onde a pessoa reside
  cep: string;         // Código de Endereçamento Postal (CEP)
  cidade: string;      // Cidade de residência
  uf: string;          // Unidade Federativa (estado) da residência
}
