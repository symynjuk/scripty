#!/bin/sh
docker run -it --rm -v "$PWD":/usr/src/mymaven -v "$HOME/.m2":/root/.m2 -v "$PWD":/usr/src/mymaven -w /usr/src/mymaven maven mvn clean package -Dmaven.test.skip=true
docker-compose up --build 