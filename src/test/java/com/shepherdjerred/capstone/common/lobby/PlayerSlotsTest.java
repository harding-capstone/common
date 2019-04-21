package com.shepherdjerred.capstone.common.lobby;

import static org.junit.Assert.assertEquals;

import com.shepherdjerred.capstone.logic.player.PlayerCount;
import org.junit.Test;

public class PlayerSlotsTest {

  @Test
  public void getFreeSlots_returnsTwo_whenCreatingNewPlayerSlotsForTwoPlayers() {
    var playerSlots = PlayerSlots.forPlayerCount(PlayerCount.TWO);

    assertEquals(2, playerSlots.getFreeSlots());
  }

  @Test
  public void getTakenSlots_returnsZero_whenCreatingNewPlayerSlotsForTwoPlayers() {
    var playerSlots = PlayerSlots.forPlayerCount(PlayerCount.TWO);

    assertEquals(0, playerSlots.getTakenSlots());
  }
}
