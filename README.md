# FintechV2 Client Java [![GitHub Packages](https://img.shields.io/badge/Maven&nbsp;package-Last&nbsp;version-lemon)](https://github.com/orgs/APIHub-CdC/packages?repo_name=bank-account-verification-client-java)



## Requirements

1. Java >= 1.7
2. Maven >= 3.3

## Installation

**Prerequisite**: get access token and access credential settings. Consult the manual **[here](https://github.com/APIHub-CdC/maven-github-packages)**.

**Step 1**: In case the configuration was integrated into the file **settingsAPIHUB.xml** (located in the root of the project), install the dependencies with the following command:

```shell
mvn --settings settingsAPIHUB.xml clean install -Dmaven.test.skip=true
```

**Step 2**: If the configuration was integrated in the  **settings.xml** of the **.m2**, install the dependencies with the following command:

```shell
mvn install -Dmaven.test.skip=true
```

## Getting started

### Step 1. Generate key and certificate
Before launching the test, you must have a keystore for the private key and the certificate associated with it. To generate the keystore, execute the instructions found in ***src/main/security/createKeystore.sh*** or with the following commands:

**Optional**:  If you want to encrypt your container, put a password in an environment variable.

```shell
export KEY_PASSWORD=your_super_secure_password
```

**Optional**: If you want to encrypt your keystore, put a password in an environment variable.

```shell
export KEYSTORE_PASSWORD=your_super_secure_keystore_password
```

- Definition of file names and aliases.

```shell
export PRIVATE_KEY_FILE=pri_key.pem
export CERTIFICATE_FILE=certificate.pem
export SUBJECT=/C=MX/ST=MX/L=MX/O=CDC/CN=CDC
export PKCS12_FILE=keypair.p12
export KEYSTORE_FILE=keystore.jks
export ALIAS=cdc
```
- Generate key and certificate.

```shell
# Generate private key
openssl ecparam -name secp384r1 -genkey -out ${PRIVATE_KEY_FILE}

# Generate public certificate
openssl req -new -x509 -days 365 \
  -key ${PRIVATE_KEY_FILE} \
  -out ${CERTIFICATE_FILE} \
  -subj "${SUBJECT}"

```

- Generate PKCS12 container from private key and certificate

```shell
# Generate PKCS12 container from private key and certificate
# You will need to package your private key and certificate.

openssl pkcs12 -name ${ALIAS} \
  -export -out ${PKCS12_FILE} \
  -inkey ${PRIVATE_KEY_FILE} \
  -in ${CERTIFICATE_FILE} \
  -password pass:${KEY_PASSWORD}

```

- Generate a dummy keystore and delete its content.

```sh
#Generate a Keystore with a pair of dummy keys.
keytool -genkey -alias dummy -keyalg RSA \
    -keysize 2048 -keystore ${KEYSTORE_FILE} \
    -dname "CN=dummy, OU=, O=, L=, S=, C=" \
    -storepass ${KEYSTORE_PASSWORD} -keypass ${KEY_PASSWORD}
#Remove the dummy key pair.
keytool -delete -alias dummy \
    -keystore ${KEYSTORE_FILE} \
    -storepass ${KEYSTORE_PASSWORD}
```

- Import the PKCS12 container to the keystore

```sh
#We import the PKCS12 container
keytool -importkeystore -srckeystore ${PKCS12_FILE} \
  -srcstoretype PKCS12 \
  -srcstorepass ${KEY_PASSWORD} \
  -destkeystore ${KEYSTORE_FILE} \
  -deststoretype JKS -storepass ${KEYSTORE_PASSWORD} \
  -alias ${ALIAS}
#List the contents of the Kesystore to verify that
keytool -list -keystore ${KEYSTORE_FILE} \
  -storepass ${KEYSTORE_PASSWORD}
```

### Step 2. Uploading the certificate within the developer portal

1. Login
2. Click on the section "**Mis aplicaciones**".
3. Select the application.
4. Go to the tab "**Certificados para @tuApp**"
   <p align="center">
     <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/applications.png">
   </p>
5. When the window opens, select the previously created certificate and click the button "**Cargar**":
   <p align="center">
     <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/upload_cert.png">
   </p>
### Step 3. Download the Círculo de Crédito certificate within the developer portal
1. Login.
2. Click on the section "**Mis aplicaciones**".
3. Select the application.
4. Go to the tab "**Certificados para @tuApp**".
   <p align="center">
       <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/applications.png">
   </p>
5. When the window opens, click the button "**Descargar**":
   <p align="center">
       <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/download_cert.png">
   </p>
### Step 4. Modify configuration file

To make use of the certificate that was downloaded and the keystore that was created, the routes found in **src/main/resources/config.properties**
```properties
keystore_file=your_path_for_your_keystore/keystore.jks
cdc_cert_file=your_path_for_certificate_of_cdc/cdc_cert.pem
keystore_password=your_super_secure_keystore_password
key_alias=cdc
key_password=your_super_secure_password
```
### Step 5. Modify URL and request data
In the WebHookSubscriptionsApiTest.java file, found at  ***src/test/java/io/bavs/client/api/***.  The request and URL data for API consumption must be modified in setBasePath ("the_url"), as shown in the following code snippet with the corresponding data:

1. Configure location and access of the key created in **step 1**and the downloaded certificate in **step 2**
- keystoreFile: location of the keystore.jks file
- cdcCertFile: location of the cdc_cert.pem file
- keystorePassword: encryption password of the keystore
- keyAlias: alias assigned to the keystore
- keyPassword: container encryption password

2. Access credentials given by Círculo de Crédito, obtained after affiliation
- usernameCDC: Círculo de Crédito user
- passwordCDC: Círculo de Crédito password

2. API consumption data
- url: URL of the API exposure
- xApiKey: Located in the application (created in ** step 2 **) of the portal and named as Consumer Key

> **NOTE:** The data in the following request are only representative.

```java

public class Fintechv22022ApiTest {
  private final Fintechv22022Api api = new Fintechv22022Api();

  private Logger logger = LoggerFactory.getLogger(Fintechv22022Api.class.getName());
  private ApiClient apiClient = null;
  private String xApiKey = "";


  @Before
  public void Setup() {
    this.apiClient = api.getApiClient();
    this.apiClient.setBasePath("https://services.circulodecredito.com.mx");
    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    apiClient.setHttpClient(okHttpClient);
  }
  @Test
  public void devuelveelScoreFINTECHTest() throws Exception {

    String xApiKey = this.xApiKey;

    Object body = null;
    RequestDatosGenerales requestDatosGenerales = new RequestDatosGenerales();
    requestDatosGenerales.setPrimerNombre("");
    requestDatosGenerales.setApellidoPaterno("");
    requestDatosGenerales.setApellidoMaterno("");
    requestDatosGenerales.setFechaNacimiento("");
    requestDatosGenerales.setDireccion("");
    requestDatosGenerales.setColoniaPoblacion("");
    requestDatosGenerales.setCiudad("");
    requestDatosGenerales.setEstado("");
    requestDatosGenerales.setPais("");
    requestDatosGenerales.setDelegacionMunicipio("");
    requestDatosGenerales.setCp("");
    requestDatosGenerales.setRfc("");
    requestDatosGenerales.setCurp("");
    requestDatosGenerales.setProducto("");

    RequestFolios requestFolios = new RequestFolios();
    requestFolios.setFolioConsulta("");
    requestFolios.setProducto("");

    String contentType = "application/json";

    ResponseScore response = api.devuelveelScoreFINTECH(xApiKey, requestDatosGenerales, contentType);
    Assert.assertNotNull(response);
    logger.info(response.toString());
  }

}
```
### Step 6. Run the unit test

Having the previous steps, all that remains is to run the unit test, with the following command:
```shell
mvn test -Dmaven.install.skip=true
```

---
[TERMS AND CONDITIONS](https://github.com/APIHub-CdC/licencias-cdc)