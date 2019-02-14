package com.shepherdjerred.capstone.common.chat;

import java.util.SortedSet;
import java.util.TreeSet;

public class ChatHistory {
  private final SortedSet<ChatMessage> messages;

  public ChatHistory() {
    this.messages = new TreeSet<>();
  }

  public SortedSet<ChatMessage> getMessages() {
    return new TreeSet<>(messages);
  }

  public SortedSet<ChatMessage> addMessage(ChatMessage message) {
    var s = new TreeSet<>(messages);
    s.add(message);
    return s;
  }
}
