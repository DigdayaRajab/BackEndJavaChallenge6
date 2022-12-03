# API Spec Ticket Reservation

Demo : https://digdayaticketbioskop.up.railway.app

- Auth
- User
- Film
- Seat
- Schedule
- Invoice


#
## Authentication Role
### Additional Access Admin and Customer Role
- User : POST
- Film : GET(filmShow)
- Film : GET(scheduleByFilmName)
### Admin Role
- Film : POST, PUT, DELETE
```json
{
  "username": "admin",
  "password": "admin"
}
```
### Customer Role
- User : PUT, DELETE
- Invoice : POST
```json
{
  "username": "customer",
  "password": "customer"
}
```
### Access to all endpoint



#
## Auth
### Log in or Sign in
Request :

- Method : POST
- EndPoint : `/api/user/signin`
- Header :
  - Content-Type : application/json
  - Accept : application/json
- Body :
```json
{
  "username": "string, unique",
  "password": "string"
}
```
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id": "Integer, unique",
    "username": "string, unique",
    "address": "string",
    "email": "email, unique",
    "password": "string",
    "role": ["ROLES"],
    "token": "string, unique",
    "type": "string, bearer"
  }
}
```


#
## User
### Create User or Sign Up
Request :
- Method : POST
- EndPoint : `/api/user/signup`
- Header :
  - Content-Type : application/json
  - Accept : application/json
- Body :
```json
{
  "username": "String, Unique",
  "address": "String",
  "email": "Email, Unique",
  "password": "String",
  "role": ["ROLES *Optional"]
}
```
    
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id": "Integer, unique",
    "username": "String, Unique",
    "address": "String",
    "email": "Email, Unique",
    "password": "String",
    "roles": [
      {
        "id": "Integer, unique",
        "name": "ROLE"
      }
    ]
  }
}
```

### Update User
Request :
- Method : PUT
- EndPoint : `/api/user/update`
- Header :
    - Authorization : bearer token
    - Content-Type : application/json
    - Accept : application/json
- Body :

```json
{
  "id": "Integer",
  "username": "String",
  "address": "String",
  "email": "String",
  "password": "String",
  "role": ["ROLES"]
}
```
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id": "Integer, unique",
    "username": "String, Unique",
    "address": "String",
    "email": "Email, Unique",
    "password": "String",
    "roles": [
      {
        "id": "Integer, unique",
        "name": "ROLE"
      }
    ]
  }
}
```

### Delete User
Request :
- Method : DELETE
- EndPoint : `/api/user/delete?id=`
- Header :
  - Authorization : bearer token
  - Accept : application/json
- Response :
```json
{
  "code": "number",
  "status": "string"
}
```

### List User
Request :

- Method : GET
- EndPoint : `/api/user/getAllUser`
- Header :- Header :
    - Authorization : bearer token
    - Accept : application/json
- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": [
    {
      "id_user": "Integer, unique",
      "username": "String",
      "address": "String",
      "email": "String",
      "password": "String",
      "roles": [
        {
          "id": "Integer, unique",
          "name": "ROLE"
        }
      ]
    },
    {
      "id_user": "Integer, unique",
      "username": "String",
      "address": "String",
      "email": "String",
      "password": "String",
      "roles": [
        {
          "id": "Integer, unique",
          "name": "ROLE"
        }
      ]
    }
  ]
}
```

### Get User by Id
Request :
- Method : GET
- EndPoint : `/api/user/search/{id_user}`
- Header :
    - Authorization : bearer token
    - Accept : application/json
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id_user": "Integer, unique",
    "username": "String",
    "address": "String",
    "email": "String",
    "password": "String",
    "roles": [
      {
        "id": "Integer, unique",
        "name": "ROLE"
      }
    ]
  }
}
```

### Get User by name
Request :
- Method : GET
- EndPoint : `/api/user/search?username=`
- Header :
    - Authorization : bearer token
    - Accept : application/json
- Params :
```json
{
  "username": "String"
}
```
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": [
    {
      "id_user": "Integer, unique",
      "username": "String",
      "address": "String",
      "email": "String",
      "password": "String",
      "roles": [
        {
          "id": "Integer, unique",
          "name": "ROLE"
        }
      ]
    },
    {
      "id_user": "Integer, unique",
      "username": "String",
      "address": "String",
      "email": "String",
      "password": "String",
      "roles": [
        {
          "id": "Integer, unique",
          "name": "ROLE"
        }
      ]
    }
  ]
}
```




















## Film

### Create Film
Request :
- Method : POST
- EndPoint : `/api/film/add`
- Header :
    - Authorization : bearer token
    - Content-Type : application/json
    - Accept : application/json
- Body :

```json
{
  "films":{
    "filmCode": "String",
    "filmName": "String",
    "isShow": "Show",
    "schedules": [
      {
        "showDate": "2022-11-28",
        "startingHour": "12.00",
        "endingHour": "13.00",
        "ticketPrice": "50000"
      },{
        "showDate": "2022-11-28",
        "startingHour": "16.00",
        "endingHour": "17.00",
        "ticketPrice": "60000"
      },{
        "showDate": "2022-11-28",
        "startingHour": "20.00",
        "endingHour": "21.00",
        "ticketPrice": "40000"
      }
    ]
  }
}
```

- Response :

```json
{
  "status": "number",
  "message": "string",
  "data": {
    "idFilm": "Integer, unique",
    "filmCode": "String",
    "filmName": "String",
    "isShow": "String",
    "schedules": [
      {
        "idSchedule": 69,
        "showDate": "2022-11-28",
        "startingHour": "12.00",
        "endingHour": "13.00",
        "ticketPrice": "50000"
      },
      {
        "idSchedule": 70,
        "showDate": "2022-11-28",
        "startingHour": "16.00",
        "endingHour": "17.00",
        "ticketPrice": "60000"
      },
      {
        "idSchedule": 71,
        "showDate": "2022-11-28",
        "startingHour": "20.00",
        "endingHour": "21.00",
        "ticketPrice": "40000"
      }
    ]
  }
}
```

### Update Film

Request :

- Method : PUT
- EndPoint : `api/film/update`
- Header :
    - Authorization : bearer token
    - Content-Type : application/json
    - Accept : application/json
- Body :
```json
{
  "idFilm": "Integer, unique",
  "filmCode": "String",
  "filmName": "String",
  "isShow": "String"
}
```

- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "idFilm": "Integer, unique",
    "filmCode": "String",
    "filmName": "String",
    "isShow": "String"
  }
}
```

### Delete Film

Request :

- Method : DELETE
- EndPoint : `api/film/delete?{id}`
- Header :
    - Authorization : bearer token
    - Accept : application/json
- Response :

```json
{
  "code": "number",
  "status": "string"
}
```

### List Film

Request :

- Method : GET
- EndPoint : `/api/film/getAllFilm`
- Header :
    - Authorization : bearer token
    - Accept : application/json
- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": [
    {
      "idFilm": "Integer, unique",
      "filmCode": "Long",
      "filmName": "String",
      "isShow": "String"
    },
    {
      "idFilm": "Integer, unique",
      "filmCode": "Long",
      "filmName": "String",
      "isShow": "String"
    }
  ]
}
```

### Get Film by id

Request :

- Method : GET
- EndPoint : `/films/search/{id_film}`
- Header :
    - Accept : application/json
- Response :

```json
{
  "status": "number",
  "message": "string",
  "data": [
    {
      "filmName": "string",
      "isShow": "string",
      "showDate": "2022-11-25",
      "startingHour": "12.00",
      "endingHour": "13.00",
      "ticketPrice": "50000"
    },
    {
      "filmName": "string",
      "isShow": "string",
      "showDate": "2022-11-25",
      "startingHour": "16.00",
      "endingHour": "17.00",
      "ticketPrice": "60000"
    }
  ]
}
```

### Get Film by name

Request :

- Method : GET
- EndPoint : `/api/film/search?`
- Header :
    - Authorization : bearer token
    - Accept : application/json
- Params :

```json
{
  "filmName": "String"
}
```

- Response :

```json
{
  "status": "number",
  "message": "string",
  "data": [
    {
      "filmName": "string",
      "isShow": "string",
      "showDate": "2022-11-25",
      "startingHour": "12.00",
      "endingHour": "13.00",
      "ticketPrice": "50000"
    },
    {
      "filmName": "string",
      "isShow": "string",
      "showDate": "2022-11-25",
      "startingHour": "16.00",
      "endingHour": "17.00",
      "ticketPrice": "60000"
    }
  ]
}
```

### Get Film by is Showing
Request :
- Method : GET
- EndPoint : `/api/film/getFilmsShow`
- Header :
    - Accept : application/json
- Response :

```json
{
  "status": "number",
  "message": "string",
  "data": [
    {
      "filmName": "string",
      "isShow": "string",
      "showDate": "2022-11-25",
      "startingHour": "12.00",
      "endingHour": "13.00",
      "ticketPrice": "50000"
    },
    {
      "filmName": "string",
      "isShow": "string",
      "showDate": "2022-11-25",
      "startingHour": "16.00",
      "endingHour": "17.00",
      "ticketPrice": "60000"
    }
  ]
}
```

### Get Schedule by film name
Request :
- Method : GET
- EndPoint : `/api/film/getFilmsSchedule?film_name=`
- Header :
    - Authorization : bearer token
    - Accept : application/json
- Params :

```json
{
  "filmName": "String"
}
```

- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": [
    {
      "idFilm": "Integer, unique",
      "filmCode": "Long",
      "filmName": "String",
      "isShow": "String",
      "Schedules": {
        "showDate": "String",
        "startingHour": "String",
        "ticketPrice": "String"
      }
    },
    {
      "idFilm": "Integer, unique",
      "filmCode": "Long",
      "filmName": "String",
      "isShow": "String",
      "Schedules": {
        "showDate": "String",
        "startingHour": "String",
        "ticketPrice": "String"
      }
    }
  ]
}
```







## Seats

### Create Seats

Request :

- Method : POST
- EndPoint : `/api/seats/add`
- Header :
    - Authorization : bearer token
    - Content-Type : application/json
    - Accept : application/json
- Body :

```json
{
  "seatId": {
    "studioName": "Character",
    "seatNo": "Integer"
  },
  "status": "String"
}
```
- Response :
```json
{
  "code": "number",
  "status": "string",
  "data": {
    "seatId": {
      "studioName": "Character",
      "seatNo": "Integer"
    },
    "status": "String"
  }
}
```

### Update Seats
Request :

- Method : PUT
- EndPoint : `/api/seats/update`
- Header :
  - Authorization : bearer token
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "seatId": {
    "studioName": "Character",
    "seatNo": "Integer"
  },
  "status": "String"
}
```

- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "seatId": {
      "studioName": "Character",
      "seatNo": "Integer"
    },
    "status": "String"
  }
}
```

### List Seats

Request :

- Method : GET
- EndPoint : `/api/seats/getAllSeats`
- Header :
    - Authorization : bearer token
    - Accept : application/json
- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": [
    {
      "seatId": {
        "studioName": "Character",
        "seatNo": "Integer"
      },
      "status": "String"
    },
    {
      "seatId": {
        "studioName": "Character",
        "seatNo": "Integer"
      },
      "status": "String"
    }
  ]
}
```
### Delete Seats

Request :

- Method : DELETE
- EndPoint : `/api/seats/delete`
- Header :
  - Authorization : bearer token
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "seatId": {
    "studioName": "Character",
    "seatNo": "Integer"
  }
}
```

- Response :

```json
{
  "code": "number",
  "status": "string"
}
```









## Schedules

### Create Schedules

Request :

- Method : POST
- EndPoint : `/api/schedules/add`
- Header :
  - Authorization : bearer token
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "showDate": "2022-12-22",
  "startingHour": "22.00",
  "endingHour": "23.00",
  "ticketPrice": "22222",
  "codeFilm": "Integer, Unique"
}
```

- Response :

```json
{
  "status": "Integer",
  "message": "String",
  "data": {
    "idSchedule": "Integer, Unique",
    "showDate": "2022-12-22",
    "startingHour": "22.00",
    "endingHour": "23.00",
    "ticketPrice": "22222",
    "fsFk": "Integer, Unique"
  }
}
```

### Update Schedules

Request :

- Method : PUT
- EndPoint : `api/schedules/update`
- Header :
  - Authorization : bearer token
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "idSchedule": "Integer, Unique",
  "showDate": "2022-12-22",
  "startingHour": "22.00",
  "endingHour": "23.00",
  "ticketPrice": "22222",
  "codeFilm": "Integer, Unique"
}
```

- Response :

```json
{
  "status": "Integer",
  "message": "String",
  "data": {
    "idSchedule": "Integer, Unique",
    "showDate": "2022-12-22",
    "startingHour": "22.00",
    "endingHour": "23.00",
    "ticketPrice": "22222",
    "fsFk": "Integer, Unique"
  }
}
```

### Delete Schedules

Request :

- Method : DELETE
- EndPoint : `api/schedules/delete?{id}`
- Header :
  - Authorization : bearer token
  - Accept : application/json
- Response :
```json
{
  "code": "number",
  "status": "string"
}
```

### List Schedules

Request :

- Method : GET
- EndPoint : `/api/schedules/getAllSchedules`
- Header :
  - Accept : application/json
- Response :

```json
{
  "status": "Integer",
  "message": "String",
  "data": [{
    "idSchedule": "Integer, Unique",
    "showDate": "2022-12-22",
    "startingHour": "22.00",
    "endingHour": "23.00",
    "ticketPrice": "22222",
    "fsFk": "Integer, Unique"
  },{
    "idSchedule": "Integer, Unique",
    "showDate": "2022-12-22",
    "startingHour": "22.00",
    "endingHour": "23.00",
    "ticketPrice": "22222",
    "fsFk": "Integer, Unique"
  }]
}
```

### Get Schedules by FilmId

Request :

- Method : GET
- EndPoint : `/api/schedules/getSchedulesByFilmId?{id}`
- Header :
  - Accept : application/json
- Response :

```json
{
  "status": "Integer",
  "message": "String",
  "data": {
    "idSchedule": "Integer, Unique",
    "showDate": "2022-12-22",
    "startingHour": "22.00",
    "endingHour": "23.00",
    "ticketPrice": "22222",
    "fsFk": "Integer, Unique"
  }
}
```






## Invoice

### Get Invoice

Request :

- Method : POST
- EndPoint : `/api/invoice/getInvoice`
- Header :
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "idUser": "Integer, unique",
  "idFilm": "Integer, unique",
  "showDate": "2022-11-25",
  "startingHour": "12.00",
  "studioName": "Character, unique",
  "seatNo": "Integer, unique"
}
```
- Response :

```
   Invoice Pdf
   Seat Status set to "ordered"
```