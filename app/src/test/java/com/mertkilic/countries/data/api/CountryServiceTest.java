package com.mertkilic.countries.data.api;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import com.mertkilic.countries.util.Constants;
import com.mertkilic.countries.util.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Mert Kilic on 2.3.2017.
 */
public class CountryServiceTest {

    MockWebServer mockServer;
    CountryApi api;

    @Before
    public void setup() throws IOException {
        mockServer = new MockWebServer();
        api = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getUnsafeOkHttpClient())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build().create(CountryApi.class);
    }

    @After
    public void dispose() throws IOException {
        mockServer.shutdown();
    }

    @Test
    public void sendsGetAllTaskRequestToTheCorrectEndpoint() throws Exception {
        enqueueMockResponse();
        api.getCountries().execute().body();
        assertGetRequestSentTo("all");
    }

    private void enqueueMockResponse() throws IOException {
        MockResponse mockResponse = new MockResponse();
        String fileContent = Utils.getStringFromRawFile(getClass()
                .getResourceAsStream("/af.json"));
        mockResponse.setResponseCode(200);
        mockResponse.setBody(fileContent);
        mockServer.enqueue(mockResponse);
        mockServer.start();
    }

    protected void assertRequestSentTo(String url) throws InterruptedException {
        RecordedRequest request = mockServer.takeRequest();
        assertEquals(url, request.getPath());
    }

    protected void assertGetRequestSentTo(String url) throws InterruptedException {
        RecordedRequest request = mockServer.takeRequest();
        assertEquals(url, request.getPath());
        assertEquals("GET", request.getMethod());
    }

    private OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
