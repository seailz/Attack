package com.sealz.attack.core.manager;

import com.sealz.attack.core.Logger;
import games.negative.framework.util.version.VersionChecker;
import lombok.Data;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getServer;

@Data
public class PluginManager {

    private final JavaPlugin plugin;

    public void checkDependencies() {
        if (!getServer().getPluginManager().isPluginEnabled("WorldEdit")) {
            Logger.log(Logger.LogLevel.ERROR, "WorldEdit is not installed! Please install WorldEdit to use this plugin.");
            Logger.log(Logger.LogLevel.ERROR, "Disabling plugin.");
            plugin.getPluginLoader().disablePlugin(plugin);
        }
    }

    public void checkVersion() {
        VersionChecker versionChecker = VersionChecker.getInstance();
        if (versionChecker.isModern()) {
            Logger.log(Logger.LogLevel.ERROR, "Your server is running a modern version of Minecraft. Attack requires a legacy version (before 1.12.2) of Minecraft to work.");
            plugin.getPluginLoader().disablePlugin(plugin);
        }
    }

    public void init() {
        checkDependencies();
        checkVersion();
    }


}
