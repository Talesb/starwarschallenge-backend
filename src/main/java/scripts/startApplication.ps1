#docker run --rm -d --network starwars-network `
#    --name startwars-collections `
#    -e MONGO_INITDB_ROOT_USERNAME=anakin `
#    -e MONGO_INITDB_ROOT_PASSWORD=padmeLoveS2 `
#    mongo
docker stop -t 0 startwars-collections
docker run --rm -d `
    --name startwars-collections `
    -e MONGO_INITDB_ROOT_USERNAME=anakin `
    -e MONGO_INITDB_ROOT_PASSWORD=padmeLoveS2 `
    -p 27017:27017 `
mongo:3.6
