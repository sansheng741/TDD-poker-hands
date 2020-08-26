package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PokerGame {

  public PokerLevel judgeCardType(String card) {
    List<Card> cards = strConvertCards(card);
    getPokerNumber(cards);

    if (isFlush(cards) && isStraight(cards)) {
      return PokerLevel.STRAIGHT_FLUSH;
    } else if (isFourOfaKind(cards)) {
      return PokerLevel.FOUR_OF_A_KIND;
    } else if(isFullHouse(cards)){
      return PokerLevel.FULL_HOUSE;
    } else if(isFlush(cards)){
      return PokerLevel.FLUSH;
    } else if(isStraight(cards)){
      return  PokerLevel.STRAIGHT;
    } else if(isThreeOfaKind(cards)){
      return  PokerLevel.THREE_OF_A_KIND;
    } else if(isTwoPairs(cards)){
      return PokerLevel.TWO_PAIRS;
    } else if(isPair(cards)){
      return PokerLevel.PAIR;
    }

    return PokerLevel.HIGH_CARD;
  }

  private boolean isPair(List<Card> cards) {
    ArrayList<List<Card>> lists = countCards(cards);
    int maxNumber = 0;
    for (List<Card> list : lists) {
      if(list.size() > maxNumber){
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
    return new ArrayList<>(cards.stream()
        .collect(Collectors.groupingBy(Card::getNumber))
        .values());
  }

  private boolean isThreeOfaKind(List<Card> cards) {
    ArrayList<List<Card>> lists = countCards(cards);
    int maxNumber = 0;
    for (List<Card> list : lists) {
      if(list.size() > maxNumber){
        maxNumber = list.size();
      }
    }
    return maxNumber == 3;
  }

  private boolean isFullHouse(List<Card> cards) {
    ArrayList<List<Card>> lists = countCards(cards);
    return (lists.get(0).size() == 3 && lists.get(1).size() == 2) || (lists.get(0).size() == 2 && lists.get(1).size() == 3);
  }

  private boolean isFourOfaKind(List<Card> cards) {
    ArrayList<List<Card>> lists = countCards(cards);
    int maxNumber = 0;
    for (List<Card> list : lists) {
      if(list.size() > maxNumber){
        maxNumber = list.size();
      }
    }
    return maxNumber == 4;
  }


  private boolean isStraight(List<Card> cards) {
    List<Card> collect = cards.stream().sorted((c1, c2) -> {
      return c1.getNumber().compareToIgnoreCase(c2.getNumber());
    }).collect(
        Collectors.toList());
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
