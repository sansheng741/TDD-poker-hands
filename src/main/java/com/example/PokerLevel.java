package com.example;

public enum PokerLevel {

  HIGH_CARD(UtilConstants.HIGH_CARD, 1),
  PAIR(UtilConstants.PAIR, 2),
  TWO_PAIRS(UtilConstants.TWO_PAIRS, 3),
  THREE_OF_A_KIND(UtilConstants.THREE_OF_A_KIND, 4),
  STRAIGHT(UtilConstants.STRAIGHT, 5),
  FLUSH(UtilConstants.FLUSH, 6),
  FULL_HOUSE(UtilConstants.FULL_HOUSE, 7),
  FOUR_OF_A_KIND(UtilConstants.FOUR_OF_A_KIND, 8),
  STRAIGHT_FLUSH(UtilConstants.STRAIGHT_FLUSH, 9);

  private final String cardType;
  private final Integer level;

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
