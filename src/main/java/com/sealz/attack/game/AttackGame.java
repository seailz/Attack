package com.sealz.attack.game;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class AttackGame {

    private ArrayList<Player> players;
    private UUID uuid;

    private Location loc;


    public AttackGame(ArrayList<Player> players) {
        setPlayers(players);
    }
}
