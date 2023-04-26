# Backend
![Java](https://img.shields.io/badge/Java-CA4245?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink)
![PostgreSQL](	https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

O backend web do ecossistema presenter foi construído a partir do Java com Spring Boot, e é o núcleo do ecossistema.

O projeto expõe uma restful api, voltada a servir informações tanto para frontend-web quanto desktop.

## Modelagem Conceitual
O domínio contém usuários padrão e administradores, com suas determinadas funções:
- USER: pode participar de eventos
- ADMIN: gerencia eventos e suas equipes 

Um usuário pode participar de um evento como espectador ou como jurado; para participar, necessita do(s) código(s), de acordo com sua função.

Eventos são entidades que possuem equipes, as quais, depois de se apresentar, poderão ser avaliadas.

![Diagrama UML](https://github.com/HenriqueSenaDev/assets/blob/main/presenter-web/PresenterWebDiagramaUML.PNG)

## Técnicas utilizadas
- estrutura de pastas vertical
- conexão e persistência no banco de dados PostgreSQL
- ORM com Data JPA e Hibernate
- fluxo controller-service-repository
- exception handling a nível de controller
- configuração de injeção de dependência
- configuração de segurança
- filtros de segurança customizados
- autenticação e autorização com Json Web Token (JWT)

## Tecnologias utilizadas
- Maven
- Java
- Spring
- JWT
- PostgreSQL

## Executando o projeto
Pré-requisitos: 
- Java +17
- variável de ambiente DB_PASSWORD: senha do usuário do banco de dados
- variável de ambiente DB_USERNAME: nome do usuário do banco de dados
- variável de ambiente DB_URL: URL do banco de dados
- variável de ambiente JWT_AUTH_SECRET: segredo para geração do tokens JWT

```bash
# clonar repositório
git clone https://github.com/HenriqueSenaDev/presenter-web.git

# entrar na pasta do projeto backend
cd backend-web

# executar o projeto
./mvnw spring-boot:run
```