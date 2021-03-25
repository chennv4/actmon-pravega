# actmon-pravega

This is a simple pravega writer developed using Pravega API with Spring Boot.

### Build 

```
mvn clean package
```

### Running app in IDE
Import the project into any IDE and build with maven build tools 

Run com.dell.sdp.pravega.ActmonPravegaApplication and edit Run Configurations.

Add VM args

```
-Dspring.profiles.active=dev -Djavax.net.debug=ssl -Djavax.net.ssl.trustStore=C:/tmp/actmon-pravega/src/main/resources/certs/dsip-psearch_truststore.jks -Djavax.net.ssl.trustStorePassword=changeit
```
truststore jks file included in resources folder

Add Environment Variables

```
pravega_client_auth_method=Bearer;pravega_client_auth_loadDynamic=true;KEYCLOAK_SERVICE_ACCOUNT_FILE=C:/Projects/customers/DataScience/psearch/dsip-demo-keycloak.json
```

Create dsip-demo-keycloak.json with this content

```
{"realm":"nautilus","auth-server-url":"https://keycloak.dsip.em.sdp.hop.lab.emc.com/auth","ssl-required":"EXTERNAL","bearer-only":false,"public-client":false,"resource":"demo-pravega","confidential-port":0,"credentials":{"secret":"1e3203a4-91ea-4454-bb1d-6cf9c0500d44"}}
```

