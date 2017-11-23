package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.*;
import com.wesleyhome.stats.feed.request.rest.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.lang.String.format;

class DefaultApiRequest implements ApiRequest {

    public static final String ROOT_URI = "https://api.mysportsfeeds.com/v1.1/pull";
    public static final String LATEST_SEASON = "latest";
    public static final String CURRENT_SEASON = "current";
    private ApiCredentials credentials;
    private String feedName;
    //    private Class<R> responseType;
    private LeagueType leagueType;
    private League league;
    private Integer startYear;
    private Map<String, String> parameters;

    DefaultApiRequest(ApiCredentials credentials, String feedName, League league,
                      Integer startYear, LeagueType leagueType) {
        this.credentials = credentials;
        this.feedName = feedName;
        this.leagueType = leagueType;
        this.league = league;
        this.startYear = startYear;
        this.parameters = new LinkedHashMap<>();
    }


    static {
        disableSslVerification();
    }

    private static void disableSslVerification() {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <R> R sendRequest(Class<R> responseType) {
        String feedUri = format("/%s/%s/%s.json", getLeague(), getSeason(), getFeedName());
        if (hasParameters()) {
            feedUri += "?" + getParameters().entrySet().stream()
                    .map(e -> format("%s=%s", e.getKey(), e.getValue()))
                    .reduce((p1, p2) -> format("%s&%s", p1, p2))
                    .orElse("");
        }
//            URL url = new URL(ROOT_URI + feedUri);
        ResponseEntity<R> responseEntity = new RestTemplateBuilder()
                .basicAuthorization(credentials.getUsername(), credentials.getPassword())
                .rootUri(ROOT_URI)
                .build()
                .exchange(feedUri, HttpMethod.GET, null, responseType);
        HttpStatus statusCode = responseEntity.getStatusCode();
//        if(statusCode.is2xxSuccessful()){
//            return responseEntity.getBody();
//        }
//        switch (statusCode){
//            case NO_CONTENT:
//                throw new NoContentException("Content for request not available yet");
//            case NOT_MODIFIED:
//            case OK:
//                return responseEntity.getBody();
//        }
        return responseEntity.getBody();

    }

    private String getFeedName() {
        return this.feedName;
    }

    private String getSeason() {
        League league = getLeague();
        LeagueType leagueType = getLeagueType();
        Integer startYear = getStartYear();
        String defaultSeason = isDefaultLatest() ? LATEST_SEASON : CURRENT_SEASON;
        if (leagueType == null) {
            return defaultSeason;
        }
        boolean multiYear = league.isMultiYear();
        return startYear == null ?
                defaultSeason :
                multiYear ?
                        format("%s-%s-%s", startYear, startYear + 1, leagueType) :
                        format("%s-%s", startYear, leagueType);
    }

    private League getLeague() {
        return this.league;
    }

    protected boolean isDefaultLatest() {
        return true;
    }

    protected Integer getStartYear() {
        return this.startYear;
    }

    protected boolean hasParameters() {
        return !getParameters().isEmpty();
    }

    protected boolean hasParameter(String key) {
        return getParameters().containsKey(key) && getParameters().get(key) != null;
    }

    protected Map<String, String> getParameters() {
        return this.parameters;
    }

    public void addParameter(String key, Object value) {
        getParameters().put(key, Objects.toString(value, null));
    }


    public DefaultApiRequest applyParameters(String listName, Iterable<?> iterable) {
        return applyParameters(listName, StreamSupport.stream(iterable.spliterator(), false));
    }

    private DefaultApiRequest applyParameters(String listName, Stream<?> stream) {
        String value = stream.map(Object::toString).reduce((v1, v2) -> String.format("%s,%s", v1, v2)).orElse(null);
        addParameter(listName, value);
        return this;
    }

    public DefaultApiRequest applyParameter(String name, Object value) {
        if (value != null) {
            addParameter(name, value.toString());
        }
        return this;
    }

    public DefaultApiRequest applyParameter(String name, ValueTransformer value) {
        return applyParameter(name, value.toStringValue());
    }

    public LeagueType getLeagueType() {
        return leagueType;
    }
}
