# Nome da Aplicação

**Descrição**: A aplicação de cadastro de pessoas permite que os usuários cadastrem, editem e visualizem informações sobre pessoas, incluindo dados como nome, CEP, endereço, bairro, cidade e UF. A aplicação é construída com Angular e segue boas práticas de validação de formulários e uso de componentes reutilizáveis.

## ![alt text](image.png) Apresentação do Projeto
- (https://youtu.be/yx_Nvz_C8ew)  

## 🛠️ Tecnologias Utilizadas

- **Frontend**: Angular 16
- **Backend**: Java Spring Boot
- **Banco de Dados**: H2 
- **Bibliotecas**: RxJS, Angular Forms, Lombok, Spring Data JPA, etc.

## 🚀 Funcionalidades

- Cadastro de Pessoas (nome, CEP, endereço, bairro, cidade, UF)
- Validação de formulário (nome não pode conter números, campo CEP no formato XXXXXXXX)
- Edição de cadastro
- Exibição de lista de pessoas em formato de tabela
- Mensagens de erro e sucesso em tempo real

## 📦 Como Rodar a Aplicação

### Pré-requisitos

Antes de rodar a aplicação, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- [Node.js](https://nodejs.org) (Versão 14 ou superior)
- [Angular CLI](https://angular.io/cli)
- [Java JDK 17+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

### Passos para Executar

1. Clone este repositório para a sua máquina local:

   ```bash
   git clone https://github.com/nilsonmazurchi/cadastroPessoa-Minsait-Backend-JavaSpringBoot.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd cadastroPessoa-Minsait
   ```

3. Instale as dependências do frontend:

   ```bash
   cd frontend
   npm install
   ```

4. Inicie o backend com Spring Boot:

   ```bash
   cd backend
   mvn spring-boot:run
   ```

5. Inicie o frontend Angular:

   ```bash
   cd frontend
   ng serve
   ```

6. Acesse a aplicação em:

   ```bash
   http://localhost:4200
   ```

## 🎮 Funcionalidades Principais

### **Frontend (Angular):**
- Cadastro de Pessoa: O usuário pode preencher um formulário com os dados da pessoa (nome, endereço, CEP, bairro, cidade, etc.), e ao submeter, os dados são enviados ao backend.
- Edição de Pessoa: A aplicação permite editar informações de uma pessoa já cadastrada, com validações de entrada (ex: nome não pode conter números, e o CEP deve seguir o formato correto).
- Exibição de Lista de Pessoas: Um componente de tabela recebe a lista de pessoas do backend e exibe todas as informações cadastradas.
- Validações no Frontend: Validar os dados do formulário antes de enviar para o backend, mostrando mensagens de erro quando o usuário não preencher os campos corretamente.
- Exibição de Mensagens de Sucesso/Erro: Depois de salvar ou editar uma pessoa, o sistema exibe uma mensagem de sucesso ou erro.
- Melhorias de UX: Como impedir a duplicação de CEP e fornecer feedback instantâneo para o usuário com as mensagens de validação.

### **Backend (Spring Boot):**
- **API RESTful**: Implementada com Spring Boot para receber e processar requisições do frontend.
- **Banco de Dados**: Utiliza H2.
- **Endpoints CRUD**:
  - `POST /pessoas` - Cadastrar pessoa
  - `GET /pessoas` - Listar todas as pessoas
  - `GET /pessoas/{id}` - Buscar pessoa pelo ID
  - `PUT /pessoas/{id}` - Atualizar pessoa
  - `DELETE /pessoas/{id}` - Remover pessoa
- **Camadas organizadas**:
  - Controller (Gerencia as requisições HTTP)
  - Service (Lógica de negócios)
  - Repository (Comunicação com o banco de dados usando Spring Data JPA)
- **Validações no Backend**: Uso de Bean Validation para garantir que os dados sejam válidos antes de salvar.

## 📄 Licença  
**MIT License** - Consulte o arquivo [LICENSE](LICENSE) para detalhes.  

**Desenvolvido por** Nilson Mazurchi  
**Minsait** - 2025  
🚀 [Veja outros projetos](https://github.com/nilsonmazurchi?tab=repositories)

