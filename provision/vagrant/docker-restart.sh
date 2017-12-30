#!/bin/bash

echo "*********************************"
echo "docker-restart.sh [START]"
echo "*********************************"

DOCKER_CMD="/usr/local/bin/docker-compose -f /vagrant/provision/docker/docker-compose.yml"
$DOCKER_CMD down
$DOCKER_CMD up --build -d

# upした状態でprune. さもないとupのときに再buildが走って、時間がかかってしまう
docker system prune -af

echo "*********************************"
echo "docker-restart.sh [END]"
echo "*********************************"
