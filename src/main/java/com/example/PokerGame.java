package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PokerGame {


  public String compareCard(String blackCard, String whiteCard) {
    PokerLevel blackCardLevel = judgeCardType(blackCard);
    PokerLevel whiteCardLevel = judgeCardType(whiteCard);

    if (whiteCardLevel.getLevel() > blackCardLevel.getLevel()) {
      return UtilConstants.WHITE_WINS;
    }
    if (whiteCardLevel.getLevel() < blackCardLevel.getLevel()) {
      return UtilConstants.BLACK_WINS;
    }
    return compareNumber(blackCard, whiteCard, blackCardLevel);
  }

  private String compareNumber(String blackCard, String whiteCard, PokerLevel pokerLevel) {
    int blackMaxNumber = 0, whiteMaxNumber = 0;
    List<Card> blackCardList = beforeCompare(blackCard);
    List<Card> whiteCardList = beforeCompare(whiteCard);
    if (pokerLevel.getCardType().equals(UtilConstants.HIGH_CARD)
        || pokerLevel.getCardType().equals(UtilConstants.STRAIGHT)
        || pokerLevel.getCardType().equals(UtilConstants.FLUSH)
        || pokerLevel.getCardType().equals(UtilConstants.STRAIGHT_FLUSH)) {
      //只需判断最大的牌
      for (int i = 0; i < blackCardList.size(); i++) {
        if (!blackCardList.get(i).getNumber().equals(whiteCardList.get(i).getNumber())) {
          blackMaxNumber = Integer.parseInt(blackCardList.get(i).getNumber());
          whiteMaxNumber = Integer.parseInt(whiteCardList.get(i).getNumber());
          if (blackMaxNumber > whiteMaxNumber) {
            return UtilConstants.BLACK_WINS;
          }
          if (blackMaxNumber < whiteMaxNumber) {
            return UtilConstants.WHITE_WINS;
          }
          return UtilConstants.TIE;
        }
      }
    }
    if (pokerLevel.getCardType().equals(UtilConstants.THREE_OF_A_KIND)
        || pokerLevel.getCardType().equals(UtilConstants.FULL_HOUSE)
        || pokerLevel.getCardType().equals(UtilConstants.FOUR_OF_A_KIND)) {
      return UtilConstants.TIE;
    }
    if (pokerLevel.getCardType().equals(UtilConstants.TWO_PAIRS)
        || pokerLevel.getCardType().equals(UtilConstants.PAIR)) {
      ArrayList<List<Card>> blackCountCards = countCards(blackCardList);
      ArrayList<List<Card>> whiteCountCards = countCards(whiteCardList);
      for (int i = 0; i < blackCountCards.size(); i++) {
        if (!blackCountCards.get(i).get(0).getNumber()
            .equals(whiteCountCards.get(i).get(0).getNumber())) {
          blackMaxNumber = Integer.parseInt(blackCountCards.get(i).get(0).getNumber());
          whiteMaxNumber = Integer.parseInt(whiteCountCards.get(i).get(0).getNumber());
          break;
        }
      }
    }
    if (blackMaxNumber > whiteMaxNumber) {
      return UtilConstants.BLACK_WINS;
    }
    if (blackMaxNumber < whiteMaxNumber) {
      return UtilConstants.WHITE_WINS;
    }
    return UtilConstants.TIE;
  }

  private List<Card> beforeCompare(String card) {
    List<Card> blackCardList = strConvertCards(card);
    getPokerNumber(blackCardList);
    blackCardList
        .sort(((c1, c2) -> Integer.parseInt(c2.getNumber()) - Integer.parseInt(c1.getNumber())));
    return blackCardList;
  }


  public PokerLevel judgeCardType(String card) {
    List<Card> cards = strConvertCards(card);
    getPokerNumber(cards);

    if (isFlush(cards) && isStraight(cards)) {
      return PokerLevel.STRAIGHT_FLUSH;
    }
    if (isFourOfaKind(cards)) {
      return PokerLevel.FOUR_OF_A_KIND;
    }
    if (isFullHouse(cards)) {
      return PokerLevel.FULL_HOUSE;
    }
    if (isFlush(cards)) {
      return PokerLevel.FLUSH;
    }
    if (isStraight(cards)) {
      return PokerLevel.STRAIGHT;
    }
    if (isThreeOfaKind(cards)) {
      return PokerLevel.THREE_OF_A_KIND;
    }
    if (isTwoPairs(cards)) {
      return PokerLevel.TWO_PAIRS;
    }
    if (isPair(cards)) {
      return PokerLevel.PAIR;
    }
    return PokerLevel.HIGH_CARD;
  }

  private boolean isPair(List<Card> cards) {
    ArrayList<List<Card>> lists = countCards(cards);
    int maxNumber = 0;
    for (List<Card> list : lists) {
      if (list.size() > maxNumber) {
        maxNumber = list.size();
      }
    }
    return maxNumber == 2;
  }

  private boolean isTwoPairs(List<Card> cards) {
    ArrayList<List<Card>> lists = countCards(cards);

    return lists.get(0).size() == 2 && lists.get(1).size() == 2;
  }

  private ArrayList<List<Card>> countCards(List<Card> cards) {
    List<List<Card>> collect = cards.stream()
        .collect(Collectors.groupingBy(Card::getNumber))
        .values().stream().sorted((e1, e2) -> {
          if (e2.size() - e1.size() != 0) {
            return e2.size() - e1.size();
          }
          return Integer.parseInt(e2.get(0).getNumber()) - Integer.parseInt(e1.get(0).getNumber());
        }).collect(Collectors.toList());
    return (ArrayList<List<Card>>) collect;
  }

  private boolean isThreeOfaKind(List<Card> cards) {
    ArrayList<List<Card>> lists = countCards(cards);
    int maxNumber = 0;
    for (List<Card> list : lists) {
      if (list.size() > maxNumber) {
        maxNumber = list.size();
      }
    }
    return maxNumber == 3;
  }

  private boolean isFullHouse(List<Card> cards) {
    ArrayList<List<Card>> lists = countCards(cards);
    return (lists.get(0).size() == 3 && lists.get(1).size() == 2) || (lists.get(0).size() == 2
        && lists.get(1).size() == 3);
  }

  private boolean isFourOfaKind(List<Card> cards) {
    ArrayList<List<Card>> lists = countCards(cards);
    int maxNumber = 0;
    for (List<Card> list : lists) {
      if (list.size() > maxNumber) {
        maxNumber = list.size();
      }
    }
    return maxNumber == 4;
  }


  private boolean isStraight(List<Card> cards) {
    List<Card> collect = cards.stream().sorted((c1, c2) -> {
      return c1.getNumber().compareToIgnoreCase(c2.getNumber());
    }).collect(Collectors.toList());
    for (int i = 0; i < collect.size() - 1; i++) {
      if (Integer.parseInt(collect.get(i).getNumber()) + 1 != Integer
          .parseInt(collect.get(i + 1).getNumber())) {
        return false;
      }
    }
    return true;
  }

  private boolean isFlush(List<Card> cards) {
    List<Card> collect = cards.stream().filter(distinctByKey(Card::getSuit))
        .collect(Collectors.toList());

    return collect.size() == 1;
  }

  private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
    Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
  }

  private List<Card> strConvertCards(String card) {
    List<Card> cardList = new ArrayList<>();
    String[] cards = card.split(" ");
    for (String c : cards) {
      Card pokerCard = new Card(c.charAt(0) + "", c.charAt(1) + "");
      cardList.add(pokerCard);
    }
    return cardList;
  }

  private void getPokerNumber(List<Card> cards) {
    for (Card card : cards) {
      switch (card.getNumber()) {
        case "T":
          card.setNumber("10");
          break;
        case "J":
          card.setNumber("11");
          break;
        case "Q":
          card.setNumber("12");
          break;
        case "K":
          card.setNumber("13");
          break;
        case "A":
          card.setNumber("14");
          break;
        default:
          break;
      }
    }
  }


}
