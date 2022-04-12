
# Global Logic
Un Api para usuarios global logic

Api REST - Orientada al manejo de usuarios, se crean usuarios y con el mismo token generado, se realizan consultas mediante la asociacion
de UUID a traves de dicho token.

#ENDPOINTS:

(POST)
- localhost:8080/api/v1/user/sign-up

Este primer endpoint necesita de un request con la siguiente estructura: 
{
    "name":"String",
    "email": "String",
    "password": "Stringgg01"
    "phones": [
        {
            "number": 01,
            "cityCode": 011,
            "countryCode": "string"
        }
    ]
   
}

Donde los campos name y phones son opcionales.

(GET)
- localhost:8080/api/v1/user/login 

El segundo endpoint de consulta usa un Bearer Token, que se genera del sing-up, con este tipo de autorizacion en una herramienta como postman, retorna
la informacion solicitada. 
 

# TECNOLOGIAS 
	-Java 8
	-Spring boot 2.6.6
	-JPA 
	-JSON Web Token + Interceptor
	-Java Validation
	-H2 
	-Junit + Mockito
	-Spring Security
	-Maven
	
# USUARIOS  (/api/v1/user/login) 

Existen usuarios ya creados en el data.sql que estan habilidatos para consultarse en el primer run (para que sea mas facil probar)

Un ejemplo del response que trae estos datos precargados, es el siguiente:
{

      "id": "1e36b448-3387-4508-ad22-92cc0528092e",
      "created": "2021-05-11T00:00:00",
      "lastLogin": "2022-04-09T00:00:00",
      "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJnbG9iYWxMb2dpY0pXVCIsInN1YiI6IjFlMzZiNDQ4LTMzODctNDUwOC1hZDIyLTkyY2MwNTI4MDkyZSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2NDk2MzEzODh9.ECuoEJpF1pqKMeyIdKpFNq8ZXnB-kEogeCdPC2mw9J_cH7XKD0ShPXIJETSIytQukUfo-8-3EB9hJT3ZHlp4Zg",
      "isActive": true,
      "name": "jesus",
      "email": "jesus@test.com",
      "password": "1234",
      "phones": [
        {
            "number": 675678,
            "cityCode": 11,
            "countryCode": "BA"
        }
    ]
      
}
	