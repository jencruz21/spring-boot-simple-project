#!/bin/sh

export DATABASE_NAME=tasks
export DATABASE_HOST=localhost
export DATABASE_USERNAME=root
export DATABASE_PASSWORD=admin
export DATABASE_PORT=3306
export USER_USERNAME=root
export USER_PASSWORD=12345

mvn clean install

docker-compose build
docker-compose up -d