# Starwarschallenge-backend
Projeto para cadastro de Planetas do filme StarWars. Projeto feito em Spring Boot e MongoDB.

Neste projeto foram utilizadas tecnologias como Docker para utilização do MongoDB de maneira mais prática, se possível execute
o comando a seguir para subir um container já configurado antes de iniciar o projeto.


## Levantando container com MongoDB

docker run --rm -d --name startwars-collections -e MONGO_INITDB_ROOT_USERNAME=anakin -e MONGO_INITDB_ROOT_PASSWORD=padmeLoveS2 -p 27017:27017 mongo:3.6

## Enpacotando o projeto

Na pasta raiz do projeto execute o comando:
> mvn clean package

Após o maven enpacotar o nosso jar, utilize o comando:
> java -jar target/starwarschallenge-0.0.1-SNAPSHOT.jar

Ou execute utilizando uma IDE da sua preferência.

## Acessando as APIs
O prefixo para acessar os endpoints do projeto é:

http://localhost:8080/starwarschallenge/api
 
Sendo assim, o endpoint disponibilizado para os planetas fica localizado em:
 
http://localhost:8080/starwarschallenge/api/planetas

Para Facilitar a visualização dos endpoints também foi implementado no projeto o Swagger, e através desta api podemos visualizar com facilidade os endpoints disponíves, basta acessar:
 
http://localhost:8080/starwarschallenge/api/swagger-ui.html
 
 
