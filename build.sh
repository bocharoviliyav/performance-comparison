#!/usr/bin/env bash

# exit when any command fails
set -e
# docker buildx build --progress=plain --platform=linux/amd64 -t micronaut:1.0  -f ./Dockerfile .
echo "Building Micronaut..."
cd micronaut && docker build -t micronaut:1.0  -f ./Dockerfile .

echo "Building Spring Boot..."
cd ../spring-boot && docker build -t spring:1.0  -f ./Dockerfile .

echo "Building Helidon..."
cd ../helidon &&  docker build -t helidon:1.0 -f ./Dockerfile .

echo "Building Quarkus..."
cd ../quarkus &&  docker build -t quarkus:1.0 -f ./Dockerfile .

echo "Building Golang..."
cd ../golang && docker build -t golang:1.0  -f ./Dockerfile .
