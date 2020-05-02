#!/bin/bash
echo "== Se convierte archivo RAML a SWAGGER =="
# https://github.com/esh-b/RAML-to-Swagger-Converter
RAML=./file-raml-swagger/api.raml
echo "== RAML a SWAGGER ==" $RAML
echo "================================================================="

export JAVA_HOME=/opt/jdk1.8.0_221/
export PATH=$JAVA_HOME/bin:$PATH

if [ -f "$RAML" ]; then
    echo "$RAML exist"
    java -jar ./lib-ramlToSwagger/raml2swagger-app.jar ./file-raml-swagger/api.raml
else 
    echo "$RAML does not exist"
fi

echo '== FIN de convercion de archivo =='
