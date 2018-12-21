package com.wesleyhome.stats.feed.request.api.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wesleyhome.stats.feed.request.api.*;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.lang.String.format;

public class DefaultApiRequest implements ApiRequest {

    public static final String ROOT_URI = "https://api.mysportsfeeds.com/v1.1/pull";
    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;
    private ApiCredentials credentials;
    private String feedName;
    private LeagueType leagueType;
    private League league;
    private Season season;
    private Integer startYear;
    private Map<String, String> parameters;

    DefaultApiRequest(
            ApiCredentials credentials,
            String feedName,
            League league,
            Integer startYear,
            Season season,
            LeagueType leagueType) {
        this.credentials = credentials;
        this.feedName = feedName;
        this.leagueType = leagueType;
        this.league = league;
        this.startYear = startYear;
        this.season = season;
        this.parameters = new LinkedHashMap<>();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(5, java.util.concurrent.TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

    }

    protected boolean hasSeason() {
        return true;
    }

    @Override
    public <R> R sendRequest(Class<R> responseType) {
        String feedUri = hasSeason() ? format("%s/%s/%s.json", getLeague(), getSeason(), getFeedName()) : format("/%s/%s.json", getLeague(), getFeedName());
        if (hasParameters()) {
            feedUri += "?" +
                    getParameters().entrySet().stream()
                            .map(e -> format("%s=%s", e.getKey(), e.getValue()))
                            .collect(Collectors.joining("&"));
        }
        String rootUri = ROOT_URI;
        return sendRequest(responseType, feedUri, rootUri);

    }

    private <R> R sendRequest(Class<R> responseType, String feedUri, String rootUri) {
        return sendRequest(responseType, feedUri, rootUri, true);
    }

    private <R> R sendRequest(Class<R> responseType, String feedUri, String rootUri, boolean retryOnFailure) {
        try {
            String basic = Credentials.basic(credentials.getApiToken(), credentials.getPassword());
            Response response = httpClient.newCall(
                    new Request.Builder()
                            .url(format("%s/%s", rootUri, feedUri))
                            .addHeader("Authorization", basic)
                            .build()
            )
                    .execute();
            if (response.isSuccessful()) {
                try (InputStream is = response.body().byteStream()) {
                    return mapper.readerFor(responseType).readValue(is);
                }
            } else {
                if (retryOnFailure) {
                    return sendRequest(responseType, feedUri, rootUri, false);
                }
                String message = response.message();
                int code = response.code();
                throw new IOException("Code: " + code + " - " + message + " URL: " + feedUri);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
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
        String value = stream.map(this::toStringValue).reduce((v1, v2) -> String.format("%s,%s", v1, v2)).orElse(null);
        return applyParameter(listName, value);
    }

    private String toStringValue(Object o) {
        return o instanceof ValueTransformer ? ((ValueTransformer) o).toStringValue() : o.toString();
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
