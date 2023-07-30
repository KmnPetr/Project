#!/bin/bash

remote_ip="46.8.19.22"
echo "Деплой будет осуществляться на хост " $remote_ip "."
echo "При необходимости изменить ip для деплоя измени в .sh значение переменной remote_ip"
echo


echo "//////////////////Maven сборка.////////////////////////////////////////////////////////"
mvn clean package

echo "//////////////////Сборка образа 'kmnpetr/project'//////////////////////////////////////"
docker build -t kmnpetr/project .

echo "//////////////////Push образа 'kmnpetr/project' на dockerhub///////////////////////////"
docker push kmnpetr/project

echo "//////////////////Подготовка сервера./////////////////////////////////////////"
ssh  root@$remote_ip  << EOF

docker compose -f /home/docker-compose.yml down

mkdir /home/nginx-src/

#необходимо будет заменить названия папок от ключей,т к сертбот прописал в них доменное имя
cp /etc/letsencrypt/live/598224.cloud4box.ru/fullchain.pem /home/nginx-src/
cp /etc/letsencrypt/live/598224.cloud4box.ru/privkey.pem /home/nginx-src/
cp /etc/letsencrypt/options-ssl-nginx.conf /home/nginx-src/
cp /etc/letsencrypt/ssl-dhparams.pem /home/nginx-src/

EOF

echo "//////////////////Копирование файла docker-compose.yml///////////////////////////////////////////////////"
scp ./docker-compose.yml root@$remote_ip:/home

echo "//////////////////Копирование файла default.conf///////////////////////////////////////////////////"
scp ./nginx-src/default.conf root@$remote_ip:/home/nginx-src

echo "//////////////////Запуск docker-compose.yml.///////////////////////////////////////////"
ssh root@$remote_ip << EOF
docker compose -f /home/docker-compose.yml up -d
EOF
echo "//////////////////docker-compose.yml запущен.//////////////////////////////////////////"

