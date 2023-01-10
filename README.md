# cloud-parking
Gerenciar um parque de estacionamento de veículos requer muita atenção e agilidade quando se trata de um grande fluxo de veículos. Devido a isso, torna-se uma tarefa inviável se executada manualmente.

Por conta disso, resolvi desenvolver esta API com o objetivo de aprimorar todo o processo de gerenciamento através do monitoramento de entrada e saída de veículos de um estacionamento

## Endpoints
|Descrição                  |Método     |Path
|---                        |---        |---
|Fazer Login                |POST        |`/api/v1/login`
|Registrar usuário          |POST        |`/api/v1/register`
|Adicionar vaga             |POST       |`/api/v1/vacancy`
|Selecionar vaga específica  |GET       |`/api/v1/vacancy/{id}`
|Selecionar todas as vagas   |GET       |`/api/v1/vacancy/all`
|Selecionar todas as vagas desocupadas  |GET  |`/api/v1/vacancy/all/unoccupied`
|Selecionar todas as vagas desocupadas de carro |GET|`/api/v1/vacancy/all/unoccupied/car`
|Selecionar todas as vagas desocupadas de motocicleta |GET  |`/api/v1/vacancy/all/unoccupied/motorcycle`
|Atualizar vaga específica  |PUT        |`/api/v1/vacancy/{id}`
|Deletar vaga específica    |DELETE     |`/api/v1/vacancy/{id}`
|Entrada de veículo         |POST       |`/api/v1/parking/entry`
|Saída de veículo           |GET        |`/api/v1/parking/exit/{id}`
|Selecionar todos os veículos|GET       |`/api/v1/vehicle/all`
|Selecionar veículo específico|GET      |`/api/v1/vehicle/{id}`
|Atualizar veículo          |PUT        |`/api/v1/vehicle/{id}`

## Controle de acesso
Esta API possui dois perfils de controle de acesso: `USERS` e `MANAGERS`.
Para acessar cada **endpoint** é necessário possuir o seguinte perfil de acesso, com base na tabela abaixo.

|Endpoint           |Perfil de acesso
|---                |---
|Fazer Login        |Não há
|Registrar usuário  | `MANAGERS`
|Adicionar vaga      |`MANAGERS`
|Selecionar vaga específica  |`USERS` e `MANAGERS`
|Selecionar todas as vagas   |`USERS` e `MANAGERS`
|Selecionar todas as vagas desocupadas  |`USERS` e `MANAGERS`
|Selecionar todas as vagas desocupadas de carro |`USERS` e `MANAGERS`
|Selecionar todas as vagas desocupadas de motocicleta   |`USERS` e `MANAGERS`
|Atualizar vaga específica  |`MANAGERS`
|Deletar vaga específica    |`MANAGERS`
|Entrada de veículo |`USERS` e `MANAGERS`|
|Saída de veículo| `USERS` e `MANAGERS`
|Selecionar todos os veículos| `USERS` e `MANAGERS`|
|Selecionar veículo específico| `USERS` e `MANAGERS`|
|Atualizar veículo|`MANAGERS`|


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

## Entrada de veículo ao estacionamento
Ao entrar um veículo ao estacionamento, informe: o tipo de veículo, placa, cor e nome do motorista pelo _body_ da requisição através do path `/api/v1/parking/entry` utilizando o método `POST`.
### headers
|KEY    |VALUE
|---    |---
|Authorization  |`token`
### body
```Json
{
   "vehicleType": "CAR",
   "placa": "000-000",
   "color": "Preto",
   "driveName": "Nome motorista"
}
```
Após isso, a aplicação retornará a seguinte resposta:
### body
Status Code: 200 OK
```Json
{
    "id": 1,
    "entryDate": "2023-01-09T21:08:05.8904734",
    "exitDate": null,
    "bill": null,
    "vacancy": {
        "id": 1,
        "number": 1,
        "status": "OCCUPIED",
        "vehicleType": "CAR"
    },
    "vehicle": {
        "id": 1,
        "vehicleType": "CAR",
        "placa": "000-000",
        "color": "Preto",
        "driveName": "Nome motorista"
    }
}
```
Outras possíveis respostas dessa requisição:
|Status code| Descrição
|--- |---|
|200| OK|
|201 |Created
|401|Unauthorized|
|403|Forbidden|
|404| Not Found|

## Saída de veículo do estacionamento
Ao sair um veículo do estacionamento, informe o `id` da entrada através do path `/api/v1/parking/exit/{id}` utilizando o método `GET`.
### headers
|KEY    |VALUE
|---    |---
|Authorization  |`token`

Após isso, a aplicação retornará a seguinte resposta:
### body
Status Code: 200 OK
```Json
{
    "id": 1,
    "entryDate": "2023-01-09T21:08:05.890473",
    "exitDate": "2023-01-09T21:19:32.305532",
    "bill": 4.675,
    "vacancy": {
        "id": 1,
        "number": 1,
        "status": "UNOCCUPIED",
        "vehicleType": "CAR"
    },
    "vehicle": {
        "id": 1,
        "vehicleType": "CAR",
        "placa": "000-000",
        "color": "Preto",
        "driveName": "Nome motorista"
    }
}
```
Outras possíveis respostas dessa requisição:
|Status code| Descrição
|--- |---|
|200| OK|
|401|Unauthorized|
|403|Forbidden|
|404| Not Found|

## Selecionar todos os veículos
Para recuperar todos os veículos, acesse o path `/api/v1/vehicle/all` utilizando o método `GET`.
### headers
|KEY    |VALUE
|---    |---
|Authorization  |`token`

Após isso, a aplicação retornará uma lista de todos os véiculos que passaram pelo estacionamento.
### body
Status Code: 200 OK
```Json
[
    {
        "id": 1,
        "vehicleType": "CAR",
        "placa": "000-000",
        "color": "Preto",
        "driveName": "Nome motorista"
    },
    {
        "id": 2,
        "vehicleType": "CAR",
        "placa": "000-000",
        "color": "Preto",
        "driveName": "Nome motorista"
    }
]
```
Outras possíveis respostas dessa requisição:
|Status code| Descrição
|--- |---|
|200| OK|
|401|Unauthorized|
|403|Forbidden|
|404| Not Found|

## Selecionar um vículo específico
Para selecionar um veículo específico, informe o `id`do véiculo através do path `/api/v1/vehicle/{id}` utilizando o método `GET`.
### headers
|KEY    |VALUE
|---    |---
|Authorization  |`token`

Após isso, a aplicação retornará a seguinte resposta:
### body
Status Code: 200 OK
```Json
{
   "id": 1,
   "vehicleType": "CAR",
   "placa": "000-000",
   "color": "Preto",
   "driveName": "Nome motorista"
}
```
Outras possíveis respostas dessa requisição:
|Status code| Descrição
|--- |---|
|200| OK|
|302|Found|
|401|Unauthorized|
|403|Forbidden|
|404| Not Found|

## Atualizar veículo
Para atualizar dados do véiculo, envie o _body_ através do path `/api/v1/vehicle/{id}` passando um `id` referente ao veículo e utilize o método `PUT`.
### headers
|KEY    |VALUE
|---    |---
|Authorization  |`token`

### body
```Json
{
   "id": 1,
   "vehicleType": "CAR",
   "placa": "000-000",
   "color": "Branco",
   "driveName": "Nome motorista"
}
```

Após isso, a aplicação retornará a seguinte resposta:
### body
Status Code: 426 Upgrade Required

```Json
{
   "id": 1,
   "vehicleType": "CAR",
   "placa": "000-000",
   "color": "Branco",
   "driveName": "Nome motorista"
}
```
Outras possíveis respostas dessa requisição:
|Status code| Descrição
|--- |---|
|201| Created|
|302|Found|
|401|Unauthorized|
|403|Forbidden|
|404| Not Found|
|426| Upgrade Required


