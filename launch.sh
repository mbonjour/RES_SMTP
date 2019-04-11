#!/bin/bash
mvn clean install
cd util
docker build -f Dockerfile1 -t mockserver .
docker build -f Dockerfile2 -t smtpprankk . -v ../config:/opt/app/
docker run -p 8282:8282 -p 2525:2525 mockerserver
docker run smtpprank
