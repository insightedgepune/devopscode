docker rm -f flask-container || true

docker pull 310383/flask-feedback-app:v1

docker run -d \
--name flask-container \
-p 5000:5000 \
--restart always \
310383/flask-feedback-app:v1
