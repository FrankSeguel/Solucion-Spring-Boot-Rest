#!/bin/bash

imagenTag="solucion/rest"
containerName="solucion_rest"
portLocal="8080"
portDocker="8080"

echo "Ejecutando Imagen Docker de $imagenTag"

echo "-> Bajando containers"
for cnt in $(docker container ls | grep "${imagenTag} " | awk '{ print $1 }'); do
  echo "->   Container:${cnt}"
  echo "->     docker stop ${cnt}"
  docker stop ${cnt}
done

echo "-> Ejecutando Imagen"
docker run -it --restart unless-stopped -d -p ${portLocal}:${portDocker} ${imagenTag}

echo '==FIN del RUN=='
