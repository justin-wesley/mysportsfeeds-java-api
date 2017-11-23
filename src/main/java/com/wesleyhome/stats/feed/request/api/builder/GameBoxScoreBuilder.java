package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.GameIdentifierPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.PlayerStatsApiPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.TeamStatsApiPlugin;

public final class GameBoxScoreBuilder extends
        RequestBuilder<GameBoxScoreBuilder> {

    public static final String FEED_NAME = "game_boxscore";
    private PlayerStatsApiPlugin<GameBoxScoreBuilder> playerStats;
    private TeamStatsApiPlugin<GameBoxScoreBuilder> teamStats;
    private GameIdentifierPlugin<GameBoxScoreBuilder> gameIdentifier;

    GameBoxScoreBuilder() {
        super(FEED_NAME);
        plugin(
                this.playerStats = new PlayerStatsApiPlugin<>(this),
                this.teamStats = new TeamStatsApiPlugin<>(this),
                this.gameIdentifier = new GameIdentifierPlugin<>(this)
        );
    }

    public PlayerStatsApiPlugin<GameBoxScoreBuilder> playerStats() {
        return playerStats;
    }

    public TeamStatsApiPlugin<GameBoxScoreBuilder> teamStats() {
        return teamStats;
    }

    public GameIdentifierPlugin<GameBoxScoreBuilder> gameId() {
        return gameIdentifier;
    }
}
