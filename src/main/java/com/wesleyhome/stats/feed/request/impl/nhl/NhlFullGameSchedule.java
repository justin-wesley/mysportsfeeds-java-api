package com.wesleyhome.stats.feed.request.impl.nhl;

import com.wesleyhome.stats.feed.request.api.*;
import com.wesleyhome.stats.feed.request.impl.DateConverters;
import com.wesleyhome.stats.feed.request.impl.SemanticDate;

import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class NhlFullGameSchedule {
    private final ApiCredentials credentials;
    private final Integer season;
    private LeagueType leagueType;
    private List<String> teams = new ArrayList<>();
    private DateConverter date;
    private List<GameStatus> statuses = new ArrayList<>();
    private String sort;
    private Integer offset;
    private Integer limit;
    private Boolean force;

    @java.beans.ConstructorProperties({"credentials", "season", "leagueType", "teams", "date", "statuses", "sort", "offset", "limit", "force"})
    NhlFullGameSchedule(ApiCredentials credentials, Integer season, LeagueType leagueType, List<String> teams, DateConverter date, List<GameStatus> statuses, String sort, Integer offset, Integer limit, Boolean force) {
        this.credentials = credentials;
        this.season = season;
        this.leagueType = leagueType;
        this.teams = teams;
        this.date = date;
        this.statuses = statuses;
        this.sort = sort;
        this.offset = offset;
        this.limit = limit;
        this.force = force;
    }

    public static NhlFullGameScheduleBuilder builder() {
        return new NhlFullGameScheduleBuilder();
    }

    public static class NhlFullGameScheduleBuilder {

        private ApiCredentials credentials;
        private Integer season;
        private LeagueType leagueType;
        private ArrayList<String> teams;
        private DateConverter date;
        private ArrayList<GameStatus> statuses;
        private String sort;
        private Integer offset;
        private Integer limit;
        private Boolean force;

        NhlFullGameScheduleBuilder() {
        }

        public NhlFullGameScheduleBuilder onDate(SemanticDate today) {
            date(today.converter());
            return this;
        }

        public NhlFullGameScheduleBuilder onDate(String date){
            date(() -> date);
            return this;
        }

        public NhlFullGameScheduleBuilder onDate(ChronoLocalDate localDate) {
            date(DateConverters.onDate(localDate));
            return this;
        }

        public NhlFullGameScheduleBuilder since(SemanticDate sDate) {
            date(DateConverters.since(sDate));
            return this;
        }

        public NhlFullGameScheduleBuilder since(ChronoLocalDate localDate) {
            date(DateConverters.since(localDate));
            return this;
        }

        public NhlFullGameScheduleBuilder since(int time, TimeUnit timeUnit) {
            date(DateConverters.since(time, timeUnit));
            return this;
        }

        public NhlFullGameScheduleBuilder until(SemanticDate sDate) {
            date(DateConverters.until(sDate));
            return this;
        }

        public NhlFullGameScheduleBuilder until(ChronoLocalDate localDate) {
            date(DateConverters.until(localDate));
            return this;
        }

        public NhlFullGameScheduleBuilder until(int time, TimeUnit timeUnit) {
            date(DateConverters.until(time, timeUnit));
            return this;
        }

        public NhlFullGameScheduleBuilder between(SingleDate from, SingleDate to) {
            date(DateConverters.between(from, to));
            return this;
        }

        public <R> NhlFullGameScheduleRequest<R> buildRequest(Class<R> responseType) {
            NhlFullGameSchedule schedule = build();
            NhlFullGameScheduleRequest<R> request;
            if(schedule.season == null) {
                request = new NhlFullGameScheduleRequest(schedule.credentials, responseType);
            }else {
                if (schedule.leagueType == null) {
                    schedule.leagueType = LeagueType.REGULAR;
                }
                request = new NhlFullGameScheduleRequest(schedule.credentials, schedule.season, schedule.leagueType, responseType);
            }
            if(!schedule.teams.isEmpty()){
                String teamValue = String.join(",", schedule.teams);
                request.addParameter("team", teamValue);
            }
            if(!schedule.statuses.isEmpty()){
                String statusValue = schedule.statuses.stream().map(GameStatus::toString).collect(Collectors.joining(","));
                request.addParameter("status", statusValue);
            }
            if(schedule.date != null){
                String dateValue = schedule.date.convert();
                request.addParameter("date", dateValue);
            }
            if(schedule.sort != null){
                request.addParameter("sort", schedule.sort);
            }
            if(schedule.offset != null){
                request.addParameter("offset", schedule.offset);
            }
            if(schedule.limit != null){
                request.addParameter("limit", schedule.limit);
            }
            if(schedule.force != null){
                request.addParameter("force", schedule.force);
            }
            return request;
        }

        public NhlFullGameScheduleBuilder credentials(ApiCredentials credentials) {
            this.credentials = credentials;
            return this;
        }

        public NhlFullGameScheduleBuilder season(Integer season) {
            this.season = season;
            return this;
        }

        public NhlFullGameScheduleBuilder leagueType(LeagueType leagueType) {
            this.leagueType = leagueType;
            return this;
        }

        public NhlFullGameScheduleBuilder team(String team) {
            if (this.teams == null) this.teams = new ArrayList<String>();
            this.teams.add(team);
            return this;
        }

        public NhlFullGameScheduleBuilder teams(Collection<? extends String> teams) {
            if (this.teams == null) this.teams = new ArrayList<String>();
            this.teams.addAll(teams);
            return this;
        }

        public NhlFullGameScheduleBuilder clearTeams() {
            if (this.teams != null)
                this.teams.clear();

            return this;
        }

        public NhlFullGameScheduleBuilder date(DateConverter date) {
            this.date = date;
            return this;
        }

        public NhlFullGameScheduleBuilder status(GameStatus status) {
            if (this.statuses == null) this.statuses = new ArrayList<GameStatus>();
            this.statuses.add(status);
            return this;
        }

        public NhlFullGameScheduleBuilder statuses(Collection<? extends GameStatus> statuses) {
            if (this.statuses == null) this.statuses = new ArrayList<GameStatus>();
            this.statuses.addAll(statuses);
            return this;
        }

        public NhlFullGameScheduleBuilder clearStatuses() {
            if (this.statuses != null)
                this.statuses.clear();

            return this;
        }

        public NhlFullGameScheduleBuilder sort(String sort) {
            this.sort = sort;
            return this;
        }

        public NhlFullGameScheduleBuilder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public NhlFullGameScheduleBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public NhlFullGameScheduleBuilder force(Boolean force) {
            this.force = force;
            return this;
        }

        public NhlFullGameSchedule build() {
            List<String> teams;
            switch (this.teams == null ? 0 : this.teams.size()) {
                case 0:
                    teams = java.util.Collections.emptyList();
                    break;
                case 1:
                    teams = java.util.Collections.singletonList(this.teams.get(0));
                    break;
                default:
                    teams = java.util.Collections.unmodifiableList(new ArrayList<String>(this.teams));
            }
            List<GameStatus> statuses;
            switch (this.statuses == null ? 0 : this.statuses.size()) {
                case 0:
                    statuses = java.util.Collections.emptyList();
                    break;
                case 1:
                    statuses = java.util.Collections.singletonList(this.statuses.get(0));
                    break;
                default:
                    statuses = java.util.Collections.unmodifiableList(new ArrayList<GameStatus>(this.statuses));
            }

            return new NhlFullGameSchedule(credentials, season, leagueType, teams, date, statuses, sort, offset, limit, force);
        }

        public String toString() {
            return "NhlFullGameSchedule.NhlFullGameScheduleBuilder(credentials=" + this.credentials + ", season=" + this.season + ", leagueType=" + this.leagueType + ", teams=" + this.teams + ", date=" + this.date + ", statuses=" + this.statuses + ", sort=" + this.sort + ", offset=" + this.offset + ", limit=" + this.limit + ", force=" + this.force + ")";
        }
    }
}
