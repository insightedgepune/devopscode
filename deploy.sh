#!/bin/bash

docker stop flask-container || true

docker rm flask-container || true


docker pull 310383/flask-feedback-app:v1


docker run -d \
--name flask-container \
-p 5001:5001 \
--restart always \
310383/flask-feedback-app:v1
