package com.sealz.attack;

import com.sealz.attack.core.Locale;
import com.sealz.attack.core.Logger;
import com.sealz.attack.core.storage.StorageType;
import games.negative.framework.BasePlugin;
import games.negative.framework.database.DatabaseInfo;
import lombok.Getter;
import lombok.Setter;
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
    private final ArrayList<Player> queue = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
        long start = System.currentTimeMillis();

        setInstance(this);

        // Set details and register things
        register(RegisterType.COMMAND);
        register(RegisterType.LISTENER);

        Locale.init(this);
        saveDefaultConfig();

        // Sets the storage type
        switch (getConfig().getString("storage-type")) {
            case "sqlite":
                setStorageType(StorageType.SQLLITE);
                Logger.log(Logger.LogLevel.INFO,"Using SQLite storage type.");
                setSqlLiteFile(new File(getDataFolder(), "sqlite.db"));
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
                break;
            default:
                Logger.log(Logger.LogLevel.ERROR,"Invalid storage type! Please check your config.yml file.");
                Logger.log(Logger.LogLevel.ERROR, "Defaulting to SQLite storage type.");

                setStorageType(StorageType.SQLLITE);
                setSqlLiteFile(new File(getDataFolder(), "database.db"));
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
