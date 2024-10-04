#!/bin/bash

APP_DIR=$(dirname "$0")
APP_NAME=inspiration-service
PORT=8080
java -Dserver.port="${PORT}" -jar "${APP_DIR}/${APP_NAME}.jar"