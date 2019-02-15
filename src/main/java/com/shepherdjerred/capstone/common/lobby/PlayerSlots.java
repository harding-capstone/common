package com.shepherdjerred.capstone.common.lobby;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.shepherdjerred.capstone.common.player.Player;
import com.shepherdjerred.capstone.logic.match.MatchSettings.PlayerCount;
import com.shepherdjerred.capstone.logic.player.PlayerId;
import java.util.HashMap;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Maps PlayerIds to Players.
 */
@ToString
@EqualsAndHashCode
final class PlayerSlots {

  private final Map<PlayerId, Player> playerIdMap;
  private final PlayerCount playerCount;

  // TODO this could be better
  static PlayerSlots forPlayerCount(PlayerCount playerCount) {
    Map<PlayerId, Player> playerIdMap = new HashMap<>();
    for (int i = 0; i < playerCount.toInt(); i++) {
      var playerId = PlayerId.fromInt(i);
      playerIdMap.put(playerId, null);
    }
    return new PlayerSlots(playerIdMap, playerCount);
  }

  private PlayerSlots(Map<PlayerId, Player> playerIdMap,
      PlayerCount playerCount) {
    this.playerIdMap = playerIdMap;
    this.playerCount = playerCount;
  }

  boolean areSlotsFull() {
    return playerCount.toInt() == playerIdMap.size();
  }

  Player getPlayer(PlayerId playerId) {
    return playerIdMap.get(playerId);
  }

  PlayerSlots removePlayer(PlayerId playerId) {
    checkNotNull(playerId);

    var newPlayerIdMap = new HashMap<>(playerIdMap);
    newPlayerIdMap.remove(playerId);
    return new PlayerSlots(newPlayerIdMap, playerCount);
  }

  // TODO extract validation?
  PlayerSlots setPlayer(PlayerId playerId, Player player) {
    checkNotNull(playerId);
    checkNotNull(player);
    checkArgument(playerId != PlayerId.NULL);

    if (playerId.toInt() > playerCount.toInt()) {
      throw new IllegalArgumentException("Player ID is invalid for this player count");
    }

    var newPlayerIdMap = new HashMap<>(playerIdMap);
    newPlayerIdMap.put(playerId, player);
    return new PlayerSlots(newPlayerIdMap, playerCount);
  }
}
