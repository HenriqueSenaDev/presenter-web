# Frontend
![TyppeScript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white)
![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![React Router](https://img.shields.io/badge/React_Router-CA4245?style=for-the-badge&logo=react-router&logoColor=white)

Construído a partir do React com Typescript, sua função é realizar o login e registro de usuários jurados e espectadores, além de interações com o evento e suas equipes.

Contem três páginas principais como também diversos modals e demais componentes customizados.
- Landing
- EventsLibrary
- Event

Após o usuário realizar seu cadastro ou login, ele pode participar de eventos a partir de códigos de participação e avaliação, como jurado ou apenas espectador.

Eventos são listados na biblioteca de eventos, e cada um tem sua tabela com as equipes disponíveis para avaliação, as quais já se apresentaram.

## Tecnologias
- ViteJS
- ReactJS
- TypeScript
- Axios
- React Router Dom

## Layout
![Landing](https://github.com/HenriqueSenaDev/assets/blob/main/presenter-web/landing.png)

![Events Library](https://github.com/HenriqueSenaDev/assets/blob/main/presenter-web/events-library.png)

![Event](https://github.com/HenriqueSenaDev/assets/blob/main/presenter-web/event.png)


## Execute o projeto
Pré-requisitos: 
- NPM ou Yarn (package manager)
- Variável de ambiente: VITE_API_URL com o link do host da [api](https://github.com/HenriqueSenaDev/presenter-web/tree/main/backend-web)

```bash
git clone https://github.com/HenriqueSenaDev/presenter-web.git
cd frontend-web
yarn install
yarn run dev
```
