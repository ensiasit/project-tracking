#!/bin/zsh

DOCKER_COMPOSE_FILE="/root/app/docker-compose.yaml"

function purge_docker() {
  if [ -f "$DOCKER_COMPOSE_FILE" ]; then
    docker-compose -f "$DOCKER_COMPOSE_FILE" down
  fi

  # purge containers
  docker_containers=$(docker container ls -a -q)
  if [ -n "$docker_containers" ]; then
    docker container rm -f "$docker_containers"
  fi

  # purge images
  docker_images=$(docker image ls -a -q)
  if [ -n "$docker_images" ]; then
    docker image rm -f "$docker_images"
  fi
}

function fetch_docker_compose() {
  if [ -f "$DOCKER_COMPOSE_FILE" ]; then
    rm "$DOCKER_COMPOSE_FILE"
  fi

  curl https://raw.githubusercontent.com/ensiasit/project-tracking/main/docker-compose.yaml --output "$DOCKER_COMPOSE_FILE"
}

purge_docker && fetch_docker_compose

docker-compose -f "$DOCKER_COMPOSE_FILE" up -d
