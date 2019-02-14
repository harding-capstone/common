package com.shepherdjerred.capstone.common.lobby;

import com.shepherdjerred.capstone.common.player.Player;
import com.shepherdjerred.capstone.logic.player.PlayerId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Lobby {
  private final LobbySettings lobbySettings;
  private final LobbyPlayers players;

  void addPlayer(Player player, PlayerId playerId) {

  }

  void removePlayer() {

  }
}
