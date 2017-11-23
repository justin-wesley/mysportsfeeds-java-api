package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.GameIdentifierBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.PlayerStatsBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.TeamStatsBuilderPlugin;

public final class GameBoxScoreBuilder extends
        RequestBuilder<GameBoxScoreBuilder> {

    public static final String FEED_NAME = "game_boxscore";
    private PlayerStatsBuilderPlugin<GameBoxScoreBuilder> playerStats;
    private TeamStatsBuilderPlugin<GameBoxScoreBuilder> teamStats;
    private GameIdentifierBuilderPlugin<GameBoxScoreBuilder> gameIdentifier;

    GameBoxScoreBuilder() {
        super(FEED_NAME);
        plugin(
                this.playerStats = new PlayerStatsBuilderPlugin<>(this),
                this.teamStats = new TeamStatsBuilderPlugin<>(this),
                this.gameIdentifier = new GameIdentifierBuilderPlugin<>(this)
        );
    }

    public PlayerStatsBuilderPlugin<GameBoxScoreBuilder> playerStats() {
        return playerStats;
    }

    public TeamStatsBuilderPlugin<GameBoxScoreBuilder> teamStats() {
        return teamStats;
    }

    public GameIdentifierBuilderPlugin<GameBoxScoreBuilder> gameId() {
        return gameIdentifier;
    }
}
