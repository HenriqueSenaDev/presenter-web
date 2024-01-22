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

Eventos possuem equipes que poderão ser avaliadas depois de se apresentar.

![Diagrama UML](https://github.com/HenriqueSenaDev/assets/blob/main/presenter-web/PresenterWebDiagramaUML.PNG)

## Técnicas utilizadas
- Estrutura de pastas vertical
- Conexão e persistência no banco de dados PostgreSQL
- ORM com Data JPA e Hibernate
- N-Tier Architecture controller-service-repository
- Exception Handling com ControllerAdvice
- Configuração de injeção de dependência (Beans)
- Configuração de Segurança (SecutiryFilterChain)
- Filtros de segurança customizados
- Autenticação e autorização com Json Web Token (JWT)
- Documentação da api com Swagger

## Tecnologias
- Java +17
- Apache Maven
- Spring Boot
- PostgreSQL
- JWT
- Swagger

## Execute o projeto
Pré-requisitos: 
- Java +17
- Apache Maven
- Variáveis de ambiente:
  - DB_PASSWORD: senha do usuário do banco de dados
  - DB_USERNAME: nome do usuário do banco de dados
  - DB_URL: URL do banco de dados
  - JWT_AUTH_SECRET: hex 256bit secret para geração de JWT
- Realizar o restore do banco através do arquivo **resources/presenter-dump-jan-2024.dump** na root do mono-repo

```bash
git clone https://github.com/HenriqueSenaDev/presenter-web.git
cd backend-web
mvn package
java -jar target/presenter-0.0.1-SNAPSHOT.jar
```
