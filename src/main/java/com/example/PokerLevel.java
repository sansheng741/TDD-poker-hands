package com.example;

public enum PokerLevel {


  HIGH_CARD("High Card", 1),
  PAIR("Pair", 2),
  TWO_PAIRS("Two Pairs", 3),
  THREE_OF_A_KIND("three of a Kind", 4),
  STRAIGHT("Straight", 5),
  FLUSH("Flush", 6),
  FULL_HOUSE("Full House", 7),
  FOUR_OF_A_KIND("Four of a Kind", 8),
  STRAIGHT_FLUSH("Straight flush", 9);

  private String cardType;
  private Integer level;

  PokerLevel(String cardType, Integer level) {
    this.cardType = cardType;
    this.level = level;
  }

  public String getCardType() {
    return cardType;
  }

  public Integer getLevel() {
    return level;
  }
}
