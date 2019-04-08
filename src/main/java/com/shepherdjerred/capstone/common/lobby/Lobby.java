package com.shepherdjerred.capstone.common.lobby;

import com.shepherdjerred.capstone.common.player.Player;
import com.shepherdjerred.capstone.logic.player.QuoridorPlayer;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public final class Lobby {

  private final LobbySettings lobbySettings;
  private final PlayerSlots playerSlots;
  private final ElementCounts elementCounts;

  public static Lobby from(LobbySettings lobbySettings) {
    var playerCount = lobbySettings.getBoardSettings().getPlayerCount();
    var playerSlots = PlayerSlots.forPlayerCount(playerCount);
    var elementCounts = new ElementCounts();
    return new Lobby(lobbySettings, playerSlots, elementCounts);
  }

  private Lobby(LobbySettings lobbySettings, PlayerSlots playerSlots, ElementCounts elementCounts) {
    this.lobbySettings = lobbySettings;
    this.playerSlots = playerSlots;
    this.elementCounts = elementCounts;
  }

  public boolean isReady() {
    var sameElement = lobbySettings.isArePlayersAllowedToUseSameElement();
    if (sameElement) {
      return areElementsUnique() && isFull();
    } else {
      return isFull();
    }
  }

  public boolean areElementsUnique() {
    return elementCounts.areUnique();
  }

  public boolean isFull() {
    return playerSlots.areSlotsFull();
  }

  public Lobby addPlayer(Player player) {
    var newPlayerSlots = playerSlots.addPlayer(player);
    var newElementCounts = elementCounts.increment(player.getElement());
    return new Lobby(lobbySettings, newPlayerSlots, newElementCounts);
  }

  public Lobby updatePlayer(QuoridorPlayer playerId, Player newPlayer) {
    var oldPlayer = playerSlots.getPlayer(playerId);
    var newElementCounts = elementCounts;
    if (oldPlayer.getElement() != newPlayer.getElement()) {
      newElementCounts = newElementCounts.decrement(oldPlayer.getElement());
      newElementCounts = newElementCounts.increment(newPlayer.getElement());
    }
    var newPlayerSlots = playerSlots.setPlayer(playerId, newPlayer);
    return new Lobby(lobbySettings, newPlayerSlots, newElementCounts);
  }

  public Lobby removePlayer(QuoridorPlayer playerId) {
    var player = playerSlots.getPlayer(playerId);
    var newPlayerSlots = playerSlots.removePlayer(playerId);
    var newElementCounts = elementCounts.decrement(player.getElement());
    return new Lobby(lobbySettings, newPlayerSlots, newElementCounts);
  }

  public Lobby removePlayer(Player player) {
    return removePlayer(playerSlots.getPlayerIdByPlayer(player));
  }
}
