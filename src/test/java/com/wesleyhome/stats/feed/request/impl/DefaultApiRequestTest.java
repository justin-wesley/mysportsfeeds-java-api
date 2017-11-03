package com.wesleyhome.stats.feed.request.impl;

import com.wesleyhome.stats.feed.request.api.ApiCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class DefaultApiRequestTest<A extends DefaultApiRequest<R>, R> {
    private A request;

    @BeforeEach
    void setUp() throws Exception{
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        Stream.of(Locale.getISOLanguages())
            .forEach(System.out::println);
        String username = resourceBundle.getString("username");
        String password = resourceBundle.getString("password");
        ApiCredentials credentials = new ApiCredentials() {
            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public String getPassword() {
                return password;
            }
        };
        request = getRequest(credentials); //new DefaultApiRequest<>(credentials, "feed", LeagueType.REGULAR, LeagueEnum.NHL);
    }

    protected abstract A getRequest(ApiCredentials credentials);


    @Test
    void sendRequest() throws Exception{
//        R response = request.sendRequest();
//        println(response);
//        assertThat(response).isNotNull();
    }

    protected abstract void println(R response) throws Exception;

}