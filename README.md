# cloud-parking
Este projeto tem como objetivo desenvolver um conjunto de APIs utilizando o Spring Boot para gerenciar um parque de estacionamento de veículos.


## Controle de acesso
Esta API possui dois perfils de controle de acesso: `USERS` e `MANAGERS`.
Para acessar cada **endpoint** é necessário possuir o seguinte perfil de acesso, com base na tabela abaixo.

|Endpoint           |Controle de acesso
|---                |---
|Fazer Login        |Não há
|Registrar usuário  | `MANAGERS`
|Adicionar vaga      |`MANAGERS`
|Selecionar vaga específica  |`USERS` e `MANAGERS`
|Selecionar todas as vagas   |`USERS` e `MANAGERS`
|Selecionar todas as vagas desocupadas  |`USERS` e `MANAGERS`
|Selecionar todas as vagas desocupadas de carro |`USERS` e `MANAGERS`
|Selecionar todas as vagas desopucadas de motocicleta   |`USERS` e `MANAGERS`
|Atualizar vaga específica  |`MANAGERS`
|Deletar vaga específica    |`USERS` e `MANAGERS`

## Criação automática do usuário inicial

Só é possível adicionar um novo usuário através de outros usuários que estejam logado  com o perfil `MANAGERS`.

Portanto, para registrar um novo usuário é necessário estar logado no sistema com esse perfil.

Por isso, a própria aplicação faz um registro de um novo usuário automaticamente caso ainda não exista nenhum usuário cadastrado na tabela do banco de dados com esse perfil.

O usuário cadastrado pelo próprio sistema possui as seguintes credenciais:
|Atributos  |Valor
|---        |---
|username:  |admin
|password:  |admin123
|roles:     |`USERS` e `MANAGERS`

## Login
Para logar no sistema, informe o `username` e o `password` no _body_ da requisição pelo path: `/api/v1/login` utilizando o método `POST`.
```Json
{
    "username": "admin",
    "password": "admin123"
{
```
Após isso, a aplicação retornará um _body_ com o `Status Code 200` contendo o `login` e o `token` de acesso.

Status code: 200 OK
```Json
{
    "login": "admin",
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2NzI5Mzc3MTksImV4cCI6MTY3Mjk0MTMxOSwiYXV0aG9yaXRpZXMiOlsiUk9MRV9NQU5BR0VSUyIsIlJPTEVfVVNFUlMiXX0.pTxSmV-xFXbtGhzFuK3Fz3JkL7jsRXq5W3zwbapTLvsdLu7gP0-F033SlbxuLM29rs8W2CmdXs3ftoW1di73dQ"
}
```

Outras possíveis respostas dessa requisição:
|Status code| Descrição
|--- |---|
|200  |OK|
|201|Created
|401|Unauthorized|
|403|Forbidden|
|404| Not Found|

## Registrar usuário
Uma vez já logado no sistema, basta adicionar um novo usuário. Para isso, preencha o header da requisição informando uma _key_ chamada `Authorization`, passando como valor o `token` retornado no login, depois informe os dados do novo usuário que pretende adicionar no _body_ da requisição no path `/api/v1/register` utilizando o método `POST`.
### Headers
|KEY    |VALUE
|---    |---
|Authorization  |`token`
### Body
```Json
{
    "name": "novoUsuario",
    "username": "novoUsuario",
    "password": "novoUsuario123",
    "roles" : [
        "USERS", "MANAGERS"
    ]
}
```
### Resposta
Após isso, a aplicação restornará a seguinte resposta:

Status Code: 200 OK
```Json
{
    "id": 2,
    "name": "novoUsuario",
    "username": "novoUsuario",
    "password": "$2a$10$fHZgBS89cJJFzHFMJ1ff1uCNWmgv9VIlSOriAKmJ2zJdIPdoc5Nva",
    "roles": [
        "USERS",
        "MANAGERS"
    ]
}
```
Outras possíveis respostas dessa requisição:
|Status code| Descrição
|--- |---|
|200  |OK|
|201|Created
|401|Unauthorized|
|403|Forbidden|
|404| Not Found|

## Adicionar vaga
Para adicionar uma vaga, informe o número da vaga, o status e tipo de veículo, se é carro ou motocicleta, no _body_ da requisição pelo path `/api/v1/vacancy` utilizando o método POST. Também é necessário informar o `token` de acesso no header.

Lembrando que é necessário possuir possuir o perfil de acesso `MANAGERS` para executar essa requisição.
### Headers
|KEY    |VALUE
|---    |---
|Authorization  |`token`
### body
```Json
{
    "number": 1,
    "status": "UNOCCUPIED",
    "vehicleType": "CAR"
}
```
### Resposta
Após isso, a aplicação restornará a seguinte resposta:

Status Code: 201 OK
```Json
{
    "id": 1,
    "number": 14,
    "status": "UNOCCUPIED",
    "vehicleType": "CAR"
}
```
Outras possíveis respostas dessa requisição:
|Status code| Descrição
|--- |---|
|200  |OK|
|201|Created
|401|Unauthorized|
|403|Forbidden|
|404| Not Found|

## Selecionar vaga
Para recuperar uma vaga, informe o `id` pelo seguinte path da requisição `/api/v1/vacancy/{id}` utilizando o método GET.

Informe o `token` de acesso pelo header.
### Headers
|KEY    |VALUE
|---    |---
|Authorization  |`token`
### Resposta
Após isso, a aplicação restornará a seguinte resposta:

Status Code: 302 FOUND
### body
```Json
{
    "id": 1,
    "number": 1,
    "status": "UNOCCUPIED",
    "vehicleType": "CAR"
}
```
Outras possíveis respostas dessa requisição:
|Status code| Descrição
|--- |---|
|200  |OK|
|302|Found
|401|Unauthorized|
|403|Forbidden|
|404| Not Found|


## Selecionando todas as vagas
Também é possivel recuperar todas as vagas, conforme a tabela abaixo.
|Descrição                  |Método        |path                     |Controle de acesso
|---                        |---           |---                       |---
|Selecionar todas as vagas  |GET           |`/api/v1/vacancy/all`     |`USERS` e `MANAGERS`
|Selecionar todas as vagas desocupadas|GET |`/api/v1/vacancy/all/unoccupied`       |`USERS` e `MANAGERS`
|Selecionar todas as vagas desocupadas de carro|GET|`/api/v1/vacancy/all/unoccupied/car`    |`USERS` e `MANAGERS`
|Selecionar todas as vagas desopucadas de motocicleta|GET|`/api/v1/vacancy/all/unoccupied/motorcycle`|`USERS` e `MANAGERS`

## Atualizar vaga
Para atualizar uma vaga, envie o _body_ contendo os novos dados, informado o `id` da vaga do qual pretende atualizar através do path `/api/v1/vacancy/{id}` utilizando o método `PUT`.
### headers
|KEY    |VALUE
|---    |---
|Authorization  |`token`
### body
```json
{
    "id": 1,
    "number": 1,
    "status": "UNOCCUPIED",
    "vehicleType": "MOTORCYCLE"
}
```

### Resposta
Após isso, a aplicação restornará a seguinte resposta:

Status Code: 426 UPGRADE REQUIRED
### body
```Json
{
    "id": 1,
    "number": 1,
    "status": "UNOCCUPIED",
    "vehicleType": "MOTORCYCLE"
}
```
Outras possíveis respostas dessa requisição:
|Status code| Descrição
|--- |---|
|426 |UPGRADE REQUIRED
|200  |OK|
|201|Created
|401|Unauthorized|
|403|Forbidden|
|404| Not Found|

## Deletar vaga
Para remover uma vaga, basta informar o `id` da vaga do qual predente remover através do path `/api/v1/vacancy/{id}` utilizando o método `DELETE` e informar o token de acesso através do header.
### headers
|KEY    |VALUE
|---    |---
|Authorization  |`token`
### Resposta
Após isso, a aplicação restornará a seguinte resposta:

Status Code: 204 NO CONTENT

Outras possíveis respostas dessa requisição:
|Status code| Descrição
|--- |---|
|204 |No Content
|401|Unauthorized|
|403|Forbidden|
|404| Not Found|


## Endpoints
|Endpoint                   |Method        |path                    |headers        |required       |Controle de acesso
|---                        |---           |---                     |---            |---            |---
|Registrar usuário          |POST          |`/api/v1/register`      |Sim            |body           |`MANAGERS`
|Fazer Login                |POST          |`/api/v1/login`         |Não            |body           |Não há
|Adicionar vaga             |POST          |`/api/v1/vacancy`       |Sim            |body           |`MANAGERS`
|Selecionar vaga específica |GET           |`/api/v1/vacancy/{id}`  |Sim            |Id             |`USERS` e `MANAGERS`
|Selecionar todas as vagas  |GET           |`/api/v1/vacancy/all`   |Sim            | -             |`USERS` e `MANAGERS`
|Selecionar todas as vagas desocupadas|GET |`/api/v1/vacancy/all/unoccupied`|Sim    | -             |`USERS` e `MANAGERS`
|Selecionar todas as vagas desocupadas de carro|GET|`/api/v1/vacancy/all/unoccupied/car`|Sim| -     |`USERS` e `MANAGERS`
|Selecionar todas as vagas desopucadas de motocicleta|GET|`/api/v1/vacancy/all/unoccupied/motorcycle`|Sim| -|`USERS` e `MANAGERS`
|Atualizar vaga específica  |PUT           |`/api/v1/vacancy/{id}`  |Sim            |Id             |`MANAGERS`
|Deletar vaga específica    |DELETE        |`/api/v1/vacancy/{id}`  |Sim            |Id             |`MANAGERS`
## Registrar usuário
|Parâmetros |Descrição  |
|---        |---        |
|method     |POST       |
|path       |`/api/v1/register`|
|headers    | Yes|
|role       |`MANAGERS`|   
|body       |required   |

#### headers
|Key   |Value|
|---   |---|
Authorization| generated token|

#### body
```Json
{
    "name": "string",
    "username": "string",
    "password": "string",
    "roles" : [
        "USERS", "MANAGERS"
    ]
}

```
### response
Status code: 200 OK
#### body
```Json
{
  "id": 0,
  "name": "string",
  "username": "string"
  "password": "string",
  "roles": [
    "USERS", "MANAGERS"
  ],
}
```
|Status code| Descrição
|--- |---|
|200  |OK|
|201|Created
|401|Unauthorized|
|403|Forbidden|
|404| Not Found|



## Entrada de veículo
```
/api/v1/parking/entry
```
Será necessário passar o seguinte corpo da solicitação
```
{
   "vehicleType": "CAR",
   "placa": "000-000",
   "color": "Branco",
   "driveName": "Nome motorista"
}
```

## Saída de veículo
```
/api/v1/parking/exit/{id}
```
Fará retornar o seguinte recurso
```
{
    "id": 22,
    "entryDate": "2022-12-28T20:59:37.487551",
    "exitDate": "2022-12-28T22:25:46.7611491",
    "bill": 36.55,
    "vacancy": {
        "id": 2,
        "number": 2,
        "status": "UNOCCUPIED",
        "vehicleType": "CAR"
    },
    "vehicle": {
        "id": 21,
        "vehicleType": "CAR",
        "placa": "523-123",
        "color": "Preto",
        "driveName": "João Pedro"
    }
}
```
