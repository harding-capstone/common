module com.shepherdjerred.capstone.common {
  requires static lombok;
  requires name.machine;
  requires com.shepherdjerred.capstone.logic;
  requires com.google.common;

  exports com.shepherdjerred.capstone.common;
  exports com.shepherdjerred.capstone.common.chat;
  exports com.shepherdjerred.capstone.common.lobby;
  exports com.shepherdjerred.capstone.common.player;
}
