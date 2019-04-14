#!/bin/bash
docker kill mocksmtp
docker rmi mockserver:latest 
docker build -f util/Dockerfile -t mockserver .
docker run --rm -p 8282:8282 -p 2525:2525 --name mocksmtp -d mockserver

