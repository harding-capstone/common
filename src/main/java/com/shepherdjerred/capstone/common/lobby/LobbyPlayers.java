package com.shepherdjerred.capstone.common.lobby;

import com.shepherdjerred.capstone.common.player.Element;
import com.shepherdjerred.capstone.common.player.Player;
import com.shepherdjerred.capstone.logic.player.PlayerId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class LobbyPlayers {
  private final LobbySettings lobbySettings;
  private final Map<UUID, Player> players;
  private final Map<Element, Integer> takenElements;
  private final Set<PlayerId> takenPlayerIds;

  public LobbyPlayers(LobbySettings lobbySettings) {
    this.lobbySettings = lobbySettings;
    this.players = new HashMap<>();
    this.takenElements = new HashMap<>();
    this.takenPlayerIds = new HashSet<>();
  }

  // TODO validation
  public void addPlayer(Player player) {
    var uuid = player.getUuid();
    players.put(uuid, player);
  }

  // TODO validation
  public void removePlayer(Player player) {
    var uuid = player.getUuid();
    players.remove(uuid);
  }

  private void incrementTakenElements() {

  }
}
