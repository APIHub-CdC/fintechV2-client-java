package io.RCCPM.client.api;

import io.RCCPM.client.ApiClient;
import io.RCCPM.client.model.RequestDatosGenerales;
import io.RCCPM.client.model.RequestFolios;
import io.RCCPM.client.model.ResponseScore;
import io.RCCPM.helper.EncriptHelper;
import io.RCCPM.interceptor.SignerInterceptor;
import okhttp3.OkHttpClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Ignore
public class Fintechv22022ApiTest {
    private final Fintechv22022Api api = new Fintechv22022Api();

    private Logger logger = LoggerFactory.getLogger(Fintechv22022Api.class.getName());
    private ApiClient apiClient = null;
    private String xApiKey = "CZRkVbF9Jq2e1nYhYxqvLN7wiGZbZS98";
    private String username ="IHM0915CMI";
    private String password = "pr3uBa8l";

    @Before
    public void Setup() {
        this.apiClient = api.getApiClient();
        this.apiClient.setBasePath("https://services.circulodecredito.com.mx");
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new SignerInterceptor())
                .build();
        apiClient.setHttpClient(okHttpClient);
    }
    @Test
    public void devuelveelScoreFINTECHTest() throws Exception {

        String xApiKey = this.xApiKey;
        String username = this.username;
        String password = this.password;

        EncriptHelper encript = new EncriptHelper(username,password);
        String xWebhookJwtAuth = encript.getCredentialJWT();

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
        requestDatosGenerales.setCp("");;
        requestDatosGenerales.setRfc("");
        requestDatosGenerales.setCurp("");
        requestDatosGenerales.setProducto("");

        RequestFolios requestFolios = new RequestFolios();
        requestFolios.setFolioConsulta("");
        requestFolios.setProducto("");

        String contentType = "application/json";

        ResponseScore response = api.devuelveelScoreFINTECH(xApiKey, username, password,requestDatosGenerales, contentType);
        Assert.assertNotNull(response);
        logger.info(response.toString());
    }
    
}
