package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.*;
import com.wesleyhome.stats.feed.request.rest.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
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

public class DefaultApiRequest implements ApiRequest {

    public static final String ROOT_URI = "https://api.mysportsfeeds.com/v1.1/pull";
    private static boolean classSetup = false;
    private ApiCredentials credentials;
    private String feedName;
    //    private Class<R> responseType;
    private LeagueType leagueType;
    private League league;
    private Season season;
    private Integer startYear;
    private Map<String, String> parameters;

    DefaultApiRequest(ApiCredentials credentials, String feedName, League league,
                      Integer startYear, Season season, LeagueType leagueType) {
        this.credentials = credentials;
        this.feedName = feedName;
        this.leagueType = leagueType;
        this.league = league;
        this.startYear = startYear;
        this.season = season;
        this.parameters = new LinkedHashMap<>();
    }


    static {
        disableSslVerification();
    }

    static void disableSslVerification() {
        if (!classSetup) {
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
                classSetup = true;
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean hasSeason() {
        return true;
    }

    @Override
    public <R> R sendRequest(Class<R> responseType) {
        String feedUri = hasSeason() ? format("/%s/%s/%s.json", getLeague(), getSeason(), getFeedName()) : format("/%s/%s.json", getLeague(), getFeedName());
        if (hasParameters()) {
            feedUri += "?" + getParameters().entrySet().stream()
                    .map(e -> format("%s=%s", e.getKey(), e.getValue()))
                    .reduce((p1, p2) -> format("%s&%s", p1, p2))
                    .orElse("");
        }
//            URL url = new URL(ROOT_URI + feedUri);
        String rootUri = ROOT_URI;
        return sendRequest(responseType, feedUri, rootUri);

    }

    protected <R> R sendRequest(Class<R> responseType, String feedUri, String rootUri) {
        ResponseEntity<R> responseEntity = new RestTemplateBuilder()
                .basicAuthorization(credentials.getUsername(), credentials.getPassword())
                .rootUri(rootUri)
                .build()
                .exchange(feedUri, HttpMethod.GET, null, responseType);
        return responseEntity.getBody();
    }

    private String getFeedName() {
        return this.feedName;
    }

    private String getSeason() {
        League league = getLeague();
        LeagueType leagueType = getLeagueType();
        Integer startYear = getStartYear();
        Season season = getSeasonEnum();
        if (season != null) {
            return season.name().toLowerCase();
        }
        season = isDefaultLatest() ? Season.LATEST : Season.CURRENT;
        String defaultSeason = season.name().toLowerCase();
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

    protected Season getSeasonEnum() {
        return this.season;
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
        Stream<?> stream = StreamSupport.stream(iterable.spliterator(), false);
        return applyParameters(listName, stream);
    }

    private DefaultApiRequest applyParameters(String listName, Stream<?> stream) {
        String value = stream.map(Object::toString).reduce((v1, v2) -> String.format("%s,%s", v1, v2)).orElse(null);
        return applyParameter(listName, value);
    }

    public DefaultApiRequest applyParameter(String name, Object value) {
        if (value != null) {
            addParameter(name, value.toString());
        }
        return this;
    }

    public DefaultApiRequest applyParameter(String name, ValueTransformer value) {
        return applyParameter(name, value == null ? null : value.toStringValue());
    }

    public LeagueType getLeagueType() {
        return leagueType;
    }
}
