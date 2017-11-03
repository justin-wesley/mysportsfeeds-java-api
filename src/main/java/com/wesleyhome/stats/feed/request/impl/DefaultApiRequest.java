package com.wesleyhome.stats.feed.request.impl;

import com.wesleyhome.stats.feed.request.RestTemplateBuilder;
import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import com.wesleyhome.stats.feed.request.api.ApiRequest;
import com.wesleyhome.stats.feed.request.api.League;
import com.wesleyhome.stats.feed.request.api.LeagueType;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.String.format;

public abstract class DefaultApiRequest<R> implements ApiRequest<R> {

    public static final String ROOT_URI = "https://api.mysportsfeeds.com/v1.1/pull";
    public static final String LATEST_SEASON = "latest";
    public static final String CURRENT_SEASON = "current";
    private ApiCredentials credentials;
    private String feedName;
    private Class<R> responseType;
    private LeagueType leagueType;
    private League league;
    private Integer startYear;
    private Map<String,String> parameters;

    protected DefaultApiRequest(ApiCredentials credentials, String feedName, League league, Integer startYear, LeagueType leagueType, Class<R> responseType) {
        this.credentials = credentials;
        this.feedName = feedName;
        this.leagueType = leagueType;
        this.league = league;
        this.startYear = startYear;
        this.responseType = responseType;
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

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    @Override
    public R sendRequest() {
        String feedUri = format("/%s/%s/%s.json", getLeague(), getSeason(), getFeedName());
        if (hasParameters()) {
            feedUri += "?" + getParameters().entrySet().stream()
                    .map(e -> format("%s=%s", e.getKey(), e.getValue()))
                    .reduce((p1, p2) -> format("%s&%s", p1, p2))
                    .orElse("");
        }
//            URL url = new URL(ROOT_URI + feedUri);
        return new RestTemplateBuilder()
                .basicAuthorization(credentials.getUsername(), credentials.getPassword())
                .rootUri(ROOT_URI)
                .build()
                .getForObject(feedUri, responseType);

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

    protected Map<String, String> getParameters() {
        return this.parameters;
    }

    public void addParameter(String key, Object value){
        getParameters().put(key, Objects.toString(value, null));
    }

    public LeagueType getLeagueType() {
        return leagueType;
    }
}
