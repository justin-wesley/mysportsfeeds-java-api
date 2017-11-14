package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.GameIdentifier;
import com.wesleyhome.stats.feed.request.api.GameIdentifierBuilder;
import com.wesleyhome.stats.feed.request.api.GameIdentifierHolder;

import java.util.ArrayList;
import java.util.List;

public class GameBoxScoreBuilder extends
        RequestBuilder<GameBoxScoreBuilder> implements GameIdentifierHolder {

    public static final String FEED_NAME = "game_boxscore";
    private List<String> teamstats = new ArrayList<>();
    private List<String> playerStats = new ArrayList<>();
    private String sort;
    private Integer offset;
    private Integer limit;
    private Boolean force;
    private GameIdentifier gameIdentifier;

    protected GameBoxScoreBuilder() {
        super(FEED_NAME);
    }


    public GameBoxScoreBuilder teamstats(List<String> teamstats) {
        this.teamstats = teamstats == null ? new ArrayList<>() : teamstats;
        return this;
    }

    public GameBoxScoreBuilder team(String team) {
        this.teamstats.add(team);
        return this;
    }


    public GameBoxScoreBuilder playerStats(List<String> playerStats) {
        this.playerStats = playerStats == null ? new ArrayList<>() : playerStats;
        return this;
    }

    public GameBoxScoreBuilder playerStat(String playerStat) {
        this.playerStats.add(playerStat);
        return this;
    }

    public GameIdentifierBuilder forGame() {
        return new GameIdentifierBuilder(this);
    }

    @Override
    public GameBoxScoreBuilder gameId(GameIdentifier gameIdentifier) {
        this.gameIdentifier = gameIdentifier;
        return this;
    }

    public GameBoxScoreBuilder sort(String sort) {
        this.sort = sort;
        return this;
    }

    public GameBoxScoreBuilder offset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public GameBoxScoreBuilder limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public GameBoxScoreBuilder force(Boolean force) {
        this.force = force;
        return this;
    }

    @Override
    protected void buildRequest(DefaultApiRequest request) {
        if (gameIdentifier == null) {
            throw new IllegalArgumentException("Game Identifier must be set");
        }
        request.applyListParameters("teamstats", teamstats)
                .applyParameter("gameid", this.gameIdentifier.toGameId())
                .applyListParameters("playerstats", playerStats)
                .applyParameter("sort", sort)
                .applyParameter("offset", offset)
                .applyParameter("limit", limit)
                .applyParameter("force", force);
    }
}
