package com.sealz.attack.profile;

import games.negative.framework.database.Database;
import games.negative.framework.database.annotation.Column;
import games.negative.framework.database.annotation.constructor.DatabaseConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.UUID;

/**
 * Stores the player's profile.
 * @author Seailz
 */
@Getter
@Setter
public class PlayerProfile {

    private UUID uuid;
    private int kills;
    private int deaths;
    private int wins;
    private int losses;
    private int games;

    @DatabaseConstructor
    public PlayerProfile(@Column("uuid") UUID uuid, @Column("kills") int kills, @Column("deaths") int deaths, @Column("wins") int wins, @Column("losses") int losses, @Column("games") int games) {
        this.uuid = uuid;
        setKills(kills);
        setDeaths(deaths);
        setWins(wins);
        setLosses(losses);
        setGames(games);
    }

    public static PlayerProfile getProfile(UUID uuid) {



        return null;
    }




}
