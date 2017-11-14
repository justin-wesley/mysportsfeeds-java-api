package com.wesleyhome.stats.feed.request.api;

public interface GameIdentifierHolder<GI extends GameIdentifierHolder<GI>> {

    default GI forGame(Integer gameId) {
        return gameId(GameIdentifiers.gId(gameId));
    }

    GI gameId(GameIdentifier gameIdentifier);
}
