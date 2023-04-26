# Frontend

O frontend web do ecossistema presenter foi construído a partir do React com Typescript.

Sua função é realizar o login e registro de usuários, em especial, jurados e espectadores, além de realizar interações com o evento e suas equipes.

Contem três páginas principais:
- Landing
- EventsLibrary
- Event

como também diversos modals e demais componentes customizados.

Após o usuário realizar seu cadastro/login, ele pode participar de eventos a partir de códigos de participação; eventos são listados na biblioteca de eventos.

Um usuário pode participar de um evento como apenas espectador, ou como jurado, através do código de jurado; cada evento tem sua tabela com as equipes disponíveis para avaliação, sendo estas as quais já se apresentaram.

## Layout
![Landing](https://github.com/HenriqueSenaDev/assets/blob/main/presenter-web/landing.png)

![Events Library](https://github.com/HenriqueSenaDev/assets/blob/main/presenter-web/events-library.png)

![Event](https://github.com/HenriqueSenaDev/assets/blob/main/presenter-web/event.png)

## Tecnologias utilizadas
- Vite
- ReactJS
- TypeScript
- Axios
- React Router Dom

## Executando o projeto
Pré-requisitos: 
- npm / yarn
- variável de ambiente VITE_API_URL contendo o host da api backend

```bash
# clonar repositório
git clone https://github.com/HenriqueSenaDev/presenter-web.git

# entrar na pasta do projeto frontend web
cd frontend-web

# instalar dependências
yarn install

# executar o projeto
yarn start
```
