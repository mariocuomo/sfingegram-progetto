#!/bin/bash

docker-compose down 
docker image rm enigmi
docker image rm enigmi-seguiti
docker image rm connessioni
docker image rm api-gateway