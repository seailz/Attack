package com.sealz.attack;

import com.sealz.attack.core.Locale;
import com.sealz.attack.core.Logger;
import com.sealz.attack.core.manager.PluginManager;
import com.sealz.attack.core.storage.StorageType;
import games.negative.framework.BasePlugin;
import games.negative.framework.database.Database;
import games.negative.framework.database.DatabaseInfo;
import games.negative.framework.util.version.VersionChecker;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

public final class AttackPlugin extends BasePlugin {

    @Getter
    @Setter
    public static AttackPlugin instance;

    @Setter
    @Getter
    private StorageType storageType;

    @Getter
    @Setter
    private File sqlLiteFile;

    @Getter
    @Setter
    private DatabaseInfo databaseInfo;

    @Getter
    @Setter
    private Database database;

    @Getter
    private final ArrayList<Player> queue = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        long start = System.currentTimeMillis();

        setInstance(this);

        new PluginManager(this).init();

        // Set details and register things
        register(RegisterType.COMMAND);
        register(RegisterType.LISTENER);

        Locale.init(this);
        saveDefaultConfig();

        // STORAGE INITIALIZATION
        switch (getConfig().getString("storage-type")) {
            case "sqlite":
                setStorageType(StorageType.SQLLITE);
                Logger.log(Logger.LogLevel.INFO,"Using SQLite storage type.");
                setSqlLiteFile(new File(getDataFolder(), "sqlite.db"));
                setDatabase(new Database(getSqlLiteFile()));
                break;
            case "mysql":
                setStorageType(StorageType.MYSQL);
                Logger.log(Logger.LogLevel.INFO,"Using MySQL storage type.");

                setDatabaseInfo(new DatabaseInfo(
                        getConfig().getString("mysql.host"),
                        getConfig().getInt("mysql.port"),
                        getConfig().getString("mysql.database"),
                        getConfig().getString("mysql.username"),
                        getConfig().getString("mysql.password")
                ));

                setDatabase(new Database(getDatabaseInfo()));
                break;
            default:
                Logger.log(Logger.LogLevel.ERROR,"Invalid storage type! Please check your config.yml file.");
                Logger.log(Logger.LogLevel.ERROR, "Defaulting to SQLite storage type.");

                setStorageType(StorageType.SQLLITE);
                setSqlLiteFile(new File(getDataFolder(), "database.db"));
                setDatabase(new Database(getSqlLiteFile()));
        }



        if (Bukkit.getWorld("attack") == null) {
            Logger.log(Logger.LogLevel.INFO, "Attack world does not exist! Creating...");
                WorldCreator wc = new WorldCreator("ATTACK");
                wc.type(WorldType.FLAT);
                wc.generatorSettings("2;0;1;");
                wc.createWorld();

            Logger.log(Logger.LogLevel.SUCCESS, "Attack world created!");
        }

        long finish = System.currentTimeMillis() - start;
        Logger.log(Logger.LogLevel.SUCCESS, "Started in " + finish + "ms!");
    }

    public void register(RegisterType type) {
        switch (type) {
            case COMMAND:
                registerCommands(
                        // Insert commands
                );
                break;
            case LISTENER:
                registerListeners(
                        // Register Listeners
                );
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public enum RegisterType {COMMAND, LISTENER}
}
