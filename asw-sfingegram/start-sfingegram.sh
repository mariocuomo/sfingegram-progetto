#!/bin/bash

gradle assemble
docker-compose build --no-cache 
docker-compose up --scale enigmi=2 --scale enigmi-seguiti=2 --scale connessioni=2
