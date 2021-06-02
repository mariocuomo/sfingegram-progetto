#!/bin/bash

gradle assemble
docker-compose up -d --scale enigmi=2 --scale enigmi-seguiti=2 --scale connessioni=2