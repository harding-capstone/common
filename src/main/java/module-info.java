module com.shepherdjerred.capstone.common {
  requires static lombok;
  requires com.google.common;
  requires com.shepherdjerred.capstone.logic;

  exports com.shepherdjerred.capstone.common;
  exports com.shepherdjerred.capstone.common.chat;
  exports com.shepherdjerred.capstone.common.lobby;
  exports com.shepherdjerred.capstone.common.player;
}
