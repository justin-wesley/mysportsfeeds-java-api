package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.GameIdentifier;
import com.wesleyhome.stats.feed.request.api.GameIdentifierBuilder;
import com.wesleyhome.stats.feed.request.api.GameIdentifierHolder;

public final class GameBoxScoreBuilder extends
        LimitBuilder<GameBoxScoreBuilder> implements GameIdentifierHolder {

    public static final String FEED_NAME = "game_boxscore";
    private ListManagerBuilder<String> teamstats = new ListManagerBuilder<>();
    private ListManagerBuilder<String> playerStats = new ListManagerBuilder<>();
    private GameIdentifier gameIdentifier;

    GameBoxScoreBuilder() {
        super(FEED_NAME);
    }


    public GameBoxScoreBuilder teamstats(String teamstat, String... teamstats) {
        this.teamstats.add(teamstat, teamstats);
        return this;
    }

    public GameBoxScoreBuilder team(String team) {
        this.teamstats.add(team);
        return this;
    }

    public GameBoxScoreBuilder playerStats(String stat, String... stats) {
        this.playerStats.add(stat, stats);
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

    @Override
    protected void buildRequest(DefaultApiRequest request) {
        if (gameIdentifier == null) {
            throw new IllegalArgumentException("Game Identifier must be set");
        }
        super.buildRequest(request);
        request.applyParameters("teamstats", teamstats)
                .applyParameter("gameid", this.gameIdentifier)
                .applyParameters("playerstats", playerStats);
    }
}
