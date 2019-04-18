package com.shepherdjerred.capstone.common.lobby;

import com.shepherdjerred.capstone.logic.board.BoardSettings;
import com.shepherdjerred.capstone.logic.match.MatchSettings;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class LobbySettings {

  private final String name;
  private final MatchSettings matchSettings;
  private final BoardSettings boardSettings;
  private final LobbyType lobbyType;
  private final boolean isDuplicateElementsEnabled;

  public enum LobbyType {
    LOCAL, NETWORK
  }
}
