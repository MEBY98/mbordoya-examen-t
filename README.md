Framework Backend Arquetipo Web
===================================

```bash
mvn archetype:generate -DarchetypeGroupId=com.mercadona.framework.cna.archetype -DarchetypeArtifactId=fwkcna-archetype-web -DarchetypeVersion=5.0.0
```

# Microservicio generado a partir de arquetipo

Toda la documentación relevante al desarrollo de este tipo de proyectos se encuentra en la guía del
desarrollador: [Guía del desarrollador](https://fwk.srv.mercadona.com/framework/spring-boot?pathname=/latest/getting-started/first-api-rest/)

Versión del arquetipo: `5.1.0`

# MINIO

Levantar servidor local: minio server /tmp/minio
Acceso a consola web: http://localhost:9000
Usuario: minioadmin
Password: minioadmin

Configuracion del bucket en application.yml:

```
fwkcna:
  buckets:
    - id: demotest-ocp-mbordoya-dev # Identificador del bucket para llamar con bucketService
      bucket-name: mbordoyalocaltest # nombre del bucket creado en el servidor
      enabled: true
      url: http://127.0.0.1:9000 # url del servidor minio
      signed-operation-token-expiration: 120
      provider: netapp
      credentials:
        access-key: minioadmin 
        secret-key: minioadmin
```

# KAFKA

En caso que el docker-compose falle, es necesario ir levantando cada contenedor en orden establecido en el archivo
docker-compose.yml

1. zookeeper
2. kafka
3. schema-registry
4. rest-proxy
5. akhq

# KAFKA-PRODUCER

Estoy configurando el serializador como String ya que AVRO requiere de clases especificas mas complejas
y archivos especificos que definen los atributos y validaciones del AVRO:
   ```
    key.serializer: org.apache.kafka.common.serialization.StringSerializer
    value.serializer: org.apache.kafka.common.serialization.StringSerializer
   ```
Esto implica tener configurado el KafkaTemplate con los tipos <String, String>

Para utilizar el KafkaAvroSerializer recomendado por el FWK de Mercadona, es necesario esta configuracion:

1. application.yaml:
    ```
       key.serializer: io.confluent.kafka.serializers.KafkaAvroSerializer
       value.serializer: io.confluent.kafka.serializers.KafkaAvroSerializer
       auto.register.schemas: true # sin esto es necesario primero crear los avro en el schema-registry de akhq
    ```
2. kafka-producer / pom.xml
   ```
   <build>
    <plugins>
      <plugin>
        <groupId>org.apache.avro</groupId>
        <artifactId>avro-maven-plugin</artifactId>
        <version>1.11.2</version>
        <executions>
          <execution>
            <phase>generate-sources</phase>
            <goals>
              <goal>schema</goal>
            </goals>
            <configuration>
              <sourceDirectory>${project.basedir}/src/main/resources/avro</sourceDirectory>
              <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
              <stringType>String</stringType>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
   </build>
   ```
3. resources/avro -> crear archivos para generar las clases de AVRO que mapean contra el mensaje de 
Kafka (esto en realidad lo da el equipo de kafka de Mercadona)
   ```
   resources/avro/exampleKeyAvro.avsc
   {
      "namespace": "com.mercadona.mbordoya.web.main.kafka.producer.kafka_models",
      "type": "record",
      "name": "ExampleKafkaKey",
      "fields": [
         {"name": "id", "type": "long"}
      ]
   }
   resources/avro/exampleValueAvro.avsc
   {
      "namespace": "com.mercadona.mbordoya.web.main.kafka.producer.kafka_models",
      "type": "record",
      "name": "ExampleKafkaValue",
      "fields": [
         {"name": "id", "type": "long"},
         {"name": "name", "type": "string"},
         {"name": "typeId", "type": "long"}
      ]
   }
   ```