Framework Backend Arquetipo Web
===================================

# ARRANQUE DE APLICACIÓN

```bash
mvn clean install
cd devops/docker/
docker-compose up -d
```

Si al arrancar docker fallan los servicios de kafka puede ser provocado porque dependen unos de otros y los contenedores
están UP antes de que los propios servicios de las imágenes estén listos. En ese caso, parar todo el docker-compose y levantar
cada contenedor en orden, esperando unos segundos entre cada uno para que el servicio este listo.

Para ejecutar únicamente el contenedor de la aplicación usando docker-compose:

```bash
mvn clean install
cd devops/docker/
docker-compose up -d --build t-app
```

# MINIO

Levantar servidor local: minio server /tmp/minio
Acceso a consola web: http://localhost:9000
Usuario: minio123
Password: minio123password

Configuración del bucket en application.yml:

```yaml
fwkcna:
  buckets:
    - id: minio_local_bucket_a
      bucket-name: localbucketa
      enabled: true
      provider: minio
      url: ${MINIO_SERVER_URL}
      trust-all-certs: true
      relax-hostname: true
      virtual-host-buckets: false
      signed-operation-token-expiration: 120
      credentials:
        access-key: ${MINIO_ACCESS_KEY}
        secret-key: ${MINIO_SECRET_KEY}
    - id: minio_local_bucket_b
      bucket-name: localbucketb
      enabled: true
      provider: minio
      url: ${MINIO_SERVER_URL}
      trust-all-certs: true
      relax-hostname: true
      virtual-host-buckets: false
      signed-operation-token-expiration: 120
      credentials:
        access-key: ${MINIO_ACCESS_KEY}
        secret-key: ${MINIO_SECRET_KEY}
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
```yaml
key.serializer: org.apache.kafka.common.serialization.StringSerializer
value.serializer: org.apache.kafka.common.serialization.StringSerializer
```
Esto implica tener configurado el KafkaTemplate con los tipos <String, String>

Para utilizar el KafkaAvroSerializer recomendado por el FWK de Mercadona, es necesario esta configuracion:

1. application.yaml:
```yaml
key.serializer: io.confluent.kafka.serializers.KafkaAvroSerializer
value.serializer: io.confluent.kafka.serializers.KafkaAvroSerializer
auto.register.schemas: true # sin esto es necesario primero crear los avro en el schema-registry de akhq
```
2. kafka-producer / pom.xml, añadir plugin para generar clases AVRO a partir de los archivos .avsc
```xml
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
```json5
//resources/avro/exampleKeyAvro.avsc
{
   "namespace": "com.mercadona.mbordoya.web.main.kafka.producer.kafka_models",
   "type": "record",
   "name": "ExampleKafkaKey",
   "fields": [
      {"name": "id", "type": "long"}
   ]
}
```
```json5
//resources/avro/exampleValueAvro.avsc
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
   
# KAFKA-CONSUMER

Reutiliza el modulo producer para obtener las clases generadas de AVRO. Es un consumidor sencillo con error handler 
que simplemente loguea el error y el mensaje que lo provoco. Esto viola hexagonal, pero es valido para un ejemplo sencillo.

# POSTGRESQL

La BBDD esta dockerizada en el docker-compose.yml, el servicio db_postgres_examples

user: sa
password: root

Para la inicializacion de datos en la BBDD se coloca en entorno local flyway desactivado y se utiliza la propiedad
de hibernate: ```ddl-auto: create-drop``` para crear y eliminar las tablas automáticamente al iniciar y parar la aplicacion.

Luego se revisan los script generados por hibernate para crear la BBDD y son copiados en las migraciones de flyway en:
```
driven/postgres-repository/sql/migration
```
ya que en un entorno real se deberia utilizar flyway para la gestion de migraciones.

He eliminado en StoreSpecification la logica para usar la funcion "unaccent" de POSTGRES ya que en la BBDD de datos 
que esta dockerizada en el proyecto el metodo no existe, mientras que en las BBDD de los entornos oficiales de Mercadona
sí que existe.
```java
private static final String UNACCENT_POSTGRESQL_FUNCTION = "unaccent";
```

# AUTHORIZATION

Se ha desarrollado una seguridad de peticiones básica para el ejemplo, utilizando un filtro que comprueba la existencia de un header
"Authorization" con un token de tipo "Bearer" generado y firmado por la propia aplicación. El token se genera en el endpoint ```auth/token```.

Los usuarios validos de la aplicacion son guardados en BBDD. Para crear un nuevo usuario se hace a través del endpoint:
```POST /user```
Con el siguiente body:
```json5
{
  "username": "user1",
  "password": "password1"
}
```
o con curl desde el terminal:
```bash
curl --location 'http://localhost:8080/user' \
--header 'Content-Type: application/json' \
--data '{
"username": "mbordoya",
"password": "123456789"
}'
```

# CONTRATO SWAGGER AUTOGENERADO

Para ver el contrato swagger autogenerado por el FWK, una vez arrancada la aplicación, se puede acceder a la url:
``` http://localhost:8080/v3/api-docs #version json ``` o ```http://localhost:8080/v3/api-docs.yaml #version yaml``` 
el cual se genera con las anotaciones que brinda el starter de springdoc-openapi en el FWK, con la siguiente dependencia,
con ello intentamos lograr un enfoque de "contract-first" en el desarrollo de las APIs REST:
```xml
<dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.6.0</version>
</dependency>
```
Además, se han creado interfaces *Api.java* en el módulo api-rest, intentando seguir de la forma más acertada el patrón
de "api-first".

# POSTMAN COLLECTION

Esta agregada la colección de Postman con las llamadas que he ido creando y utilizando para probar la aplicación:
```
/driving/api-rest/postman
```

# i18n

Para la internacionalización de mensajes se han creado los archivos messages.properties (español) 
y messages_pt.properties (portugués)

Para probar la internacionalización, se puede añadir el header "Accept-Language" en las peticiones, con los valores "es" o "pt".
Si no se añade el header, el idioma por defecto es español. Se ha insertado un interceptor encargado de capturar el 
header y settear en el locale correspondiente.

Para los mensajes excepciones basta con definir los códigos setteados en los contructores de las excepciones. Ejemplo:
```java
public class ExampleNotFoundException extends ApplicationException {

  public ExampleNotFoundException(final Long id) {
    super(400, "EXAMPLE_NOT_FOUND_ERROR_CODE", "EXAMPLE_NOT_FOUND_ERROR_CODE_DETAIL", id);
  }
}
```
```properties
#messages.properties messages_es.properties 
EXAMPLE_NOT_FOUND_ERROR_CODE=Ejemplo no encontrado
EXAMPLE_NOT_FOUND_ERROR_CODE_DETAIL=No se ha encontrado el ejemplo con id {0}
```
```properties
# messages_pr.properties
EXAMPLE_NOT_FOUND_ERROR_CODE=Exemplo não encontrado
EXAMPLE_NOT_FOUND_ERROR_CODE_DETAIL=Não foi encontrado o exemplo com id {0}
```

# CONSIDERACIONES DEL DESARROLLADOR

1. He creado la lógica de crear los objetos de respuesta en las propias clases de los DTOs en la capa de api-rest, en
un caso real eso no seria posible debido que el artefacto del contrato vendría como una dependencia del POM generado por la
pipeline de cloudbees "contract-first", en ese caso se aplicaría el patron "assembler", que es básicamente un mapper vitaminado,
donde implementaría toda esa lógica de mapeos y creación de objetos. https://www.arquitecturajava.com/dto-assembler-un-patron-de-diseno/
2. Para la subido del fichero CSV con los datos del stock de los productos, se ha dockerizado minio como bucket y el 
archivo CSV es guardado en el bucket de forma local, devolviendo en la respuesta de la API una URL para descargar dicho archivo.
3. Los tests mas significativos seria: 
   1. StoreUseCaseTest.updateStore() ya que se aprecia el uso de ArgumentCaptor.
   2. StoreControllerTest ya que se aprecia el uso de MockMvc.
   3. StoreServiceTest ya que se aprecia el caso donde se captura la excepción
   4. StoreDbAdapterTest caso sencillo de test de repositorio
4. He creado historificacion para las tablas StoreMO, ModuleMO, StoreStorageMO que serian las tablas claves del dominio
de la apliacación para en caso de querer auditar también el resto de tablas, la configuración es básicamente agregar:
```java
@Audited
public class StoreMO {}
```
en la clase de modelo que se quiere historificar, verificar que en la migración se crean las tablas:
```sql
create table revinfo (
        rev integer not null,
        revtstmp bigint,
        primary key (rev)
    )
```
y las tablas de auditoría de las clases anotadas, en este caso serían: store_aud, module_aud, store_storage_aud
5. De la parte 4 he logrado hacer el inicio del procesamiento de los tres primeros tipos de movimientos, no me ha 
alcanzado el tiempo para hacer el resto, quedan reflejado los //TODO de los mismos, la lógica sería parecida, 
seguir modificando los ModuleStock y StoreStorageStock en diferentes process y como últimos pasos realizar el update
masivo de ambas listas de entidades.