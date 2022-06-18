package com.sealz.attack;

import com.sealz.attack.core.Locale;
import com.sealz.attack.core.Logger;
import games.negative.framework.BasePlugin;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public final class AttackPlugin extends BasePlugin {

    @Getter
    @Setter
    public static AttackPlugin instance;

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
