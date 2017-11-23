package com.wesleyhome.stats.feed.request.api.builder;

import com.wesleyhome.stats.feed.request.api.builder.plugins.PlayerBuilderPlugin;
import com.wesleyhome.stats.feed.request.api.builder.plugins.PlayerStatsBuilderPlugin;

public class PlayerGameLogsBuilder extends GameLogsBuilder<PlayerGameLogsBuilder> {

    public static final String FEED_NAME = "player_gamelogs";
    private final PlayerStatsBuilderPlugin<PlayerGameLogsBuilder> playerstats;
    private final PlayerBuilderPlugin<PlayerGameLogsBuilder> player;

    public PlayerGameLogsBuilder() {
        super(FEED_NAME);
        plugin(
                this.playerstats = new PlayerStatsBuilderPlugin<>(this),
                this.player = new PlayerBuilderPlugin<>(this)
        );
    }

    public PlayerStatsBuilderPlugin<PlayerGameLogsBuilder> playerstats() {
        return playerstats;
    }

    public PlayerBuilderPlugin<PlayerGameLogsBuilder> players() {
        return player;
    }
}
