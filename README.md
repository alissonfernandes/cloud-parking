# cloud-parking
Este projeto tem como objetivo desenvolver um conjunto de APIs utilizando o Spring Boot para gerenciar um parque de estacionamento de veículos.

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
