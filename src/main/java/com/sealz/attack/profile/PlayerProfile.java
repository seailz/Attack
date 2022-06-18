package com.sealz.attack.profile;

import com.sealz.attack.AttackPlugin;
import games.negative.framework.database.Database;
import games.negative.framework.database.annotation.Column;
import games.negative.framework.database.annotation.constructor.DatabaseConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
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

    /**
     * Saves the player's profile to the database.
     */
    public void save() {
        Database db = AttackPlugin.getInstance().getDatabase();
        try {
            db.insert("attack_profiles", this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * Reads the player's profile from the database.
     * @param uuid The player's UUID.
     * @return The player's profile.
     */
    @Nullable
    public static PlayerProfile getProfile(UUID uuid) {
        Database database = AttackPlugin.getInstance().getDatabase();
        PlayerProfile profile = null;
        try {
            profile = (PlayerProfile) database.get("attack_profiles", "uuid", String.valueOf(uuid), PlayerProfile.class);
        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException ignored) {}
        return profile;
    }




}
