package com.shepherdjerred.capstone.common.lobby;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.shepherdjerred.capstone.common.player.Player;
import com.shepherdjerred.capstone.logic.player.PlayerCount;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import java.util.HashMap;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Maps QuoridorPlayers to Players.
 */
@ToString
@EqualsAndHashCode
final class PlayerSlots {

  private final Map<QuoridorPlayer, Player> playerIdMap;
  private final PlayerCount playerCount;

  // TODO this could be better
  static PlayerSlots forPlayerCount(PlayerCount playerCount) {
    Map<QuoridorPlayer, Player> playerIdMap = new HashMap<>();
    for (int i = 1; i < playerCount.toInt(); i++) {
      var playerId = QuoridorPlayer.fromInt(i);
      playerIdMap.put(playerId, null);
    }
    return new PlayerSlots(playerIdMap, playerCount);
  }

  private PlayerSlots(Map<QuoridorPlayer, Player> playerIdMap,
      PlayerCount playerCount) {
    this.playerIdMap = playerIdMap;
    this.playerCount = playerCount;
  }

  boolean areSlotsFull() {
    return playerCount.toInt() == playerIdMap.size();
  }

  Player getPlayer(QuoridorPlayer playerId) {
    return playerIdMap.get(playerId);
  }

  PlayerSlots removePlayer(QuoridorPlayer playerId) {
    checkNotNull(playerId);

    var newQuoridorPlayerMap = new HashMap<>(playerIdMap);
    newQuoridorPlayerMap.remove(playerId);
    return new PlayerSlots(newQuoridorPlayerMap, playerCount);
  }

  // TODO extract validation?
  PlayerSlots setPlayer(QuoridorPlayer playerId, Player player) {
    checkNotNull(playerId);
    checkNotNull(player);
    checkArgument(playerId != QuoridorPlayer.NULL);

    if (playerId.toInt() > playerCount.toInt()) {
      throw new IllegalArgumentException("Player ID is invalid for this player count");
    }

    var newQuoridorPlayerMap = new HashMap<>(playerIdMap);
    newQuoridorPlayerMap.put(playerId, player);
    return new PlayerSlots(newQuoridorPlayerMap, playerCount);
  }

  PlayerSlots addPlayer(Player player) {
    checkNotNull(player);
    return setPlayer(QuoridorPlayer.fromInt(playerIdMap.size() + 1), player);
  }
}
