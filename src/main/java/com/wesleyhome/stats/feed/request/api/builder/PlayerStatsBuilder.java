package com.wesleyhome.stats.feed.request.api.builder;

public abstract class PlayerStatsBuilder<B extends PlayerStatsBuilder<B>> extends
        LimitBuilder<B> {

    private ListManagerBuilder<String> teams = new ListManagerBuilder<>();
    private ListManagerBuilder<String> players = new ListManagerBuilder<>();
    private ListManagerBuilder<String> positions = new ListManagerBuilder<>();
    private ListManagerBuilder<String> countries = new ListManagerBuilder<>();
    private ListManagerBuilder<String> playerStats = new ListManagerBuilder<>();

    protected PlayerStatsBuilder(String feedName) {
        super(feedName);
    }


    public B teams(String team, String... additionalTeams) {
        this.teams.add(team, additionalTeams);
        return SELF;
    }

    public B players(String player, String... additionalPlayer) {
        this.players.add(player, additionalPlayer);
        return SELF;
    }

    public B positions(String position, String... positions) {
        this.positions.add(position, positions);
        return SELF;
    }

    public B countries(String country, String... countries) {
        this.countries.add(country, countries);
        return SELF;
    }

    public B playerStats(String stat, String... stats) {
        this.playerStats.add(stat, stats);
        return SELF;
    }

    @Override
    protected void buildRequest(DefaultApiRequest request) {
        super.buildRequest(request);
        request.applyParameters("team", teams)
                .applyParameters("countries", countries)
                .applyParameters("positions", positions)
                .applyParameters("players", players)
                .applyParameters("playerstats", playerStats);
    }
}
