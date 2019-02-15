package com.shepherdjerred.capstone.common.lobby;

import com.shepherdjerred.capstone.common.player.Player;
import com.shepherdjerred.capstone.logic.player.PlayerId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public final class Lobby {

  private final LobbySettings lobbySettings;
  private final PlayerSlots playerSlots;
  private final ElementCounts elementCounts;

  public static Lobby fromLobbySettings(LobbySettings lobbySettings) {
    var playerCount = lobbySettings.getMatchSettings().getBoardSettings().getPlayerCount();
    var playerSlots = PlayerSlots.forPlayerCount(playerCount);
    var elementCounts = new ElementCounts();
    return new Lobby(lobbySettings, playerSlots, elementCounts);
  }

  private Lobby(LobbySettings lobbySettings, PlayerSlots playerSlots, ElementCounts elementCounts) {
    this.lobbySettings = lobbySettings;
    this.playerSlots = playerSlots;
    this.elementCounts = elementCounts;
  }

  public boolean areElementsUnique() {
    return elementCounts.areUnique();
  }

  public boolean isFull() {
    return playerSlots.areSlotsFull();
  }

  public Lobby addPlayer(PlayerId playerId, Player player) {
    var newPlayerSlots = playerSlots.setPlayer(playerId, player);
    var newElementCounts = elementCounts.increment(player.getElement());
    return new Lobby(lobbySettings, newPlayerSlots, newElementCounts);
  }

  public Lobby updatePlayer(PlayerId playerId, Player newPlayer) {
    var oldPlayer = playerSlots.getPlayer(playerId);
    var newElementCounts = elementCounts;
    if (oldPlayer.getElement() != newPlayer.getElement()) {
      newElementCounts = newElementCounts.decrement(oldPlayer.getElement());
      newElementCounts = newElementCounts.increment(newPlayer.getElement());
    }
    var newPlayerSlots = playerSlots.setPlayer(playerId, newPlayer);
    return new Lobby(lobbySettings, newPlayerSlots, newElementCounts);
  }

  public Lobby removePlayer(PlayerId playerId) {
    var player = playerSlots.getPlayer(playerId);
    var newPlayerSlots = playerSlots.removePlayer(playerId);
    var newElementCounts = elementCounts.decrement(player.getElement());
    return new Lobby(lobbySettings, newPlayerSlots, newElementCounts);
  }
}
