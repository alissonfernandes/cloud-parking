# cloud-parking
Este projeto tem como objetivo desenvolver um conjunto de APIs utilizando o Spring Boot para gerenciar um parque de estacionamento de veículos.

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
