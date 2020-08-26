package com.example;

import java.util.ArrayList;
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

    boolean flush = isFlush(cards);
    boolean straight = isStraight(cards);

    if(flush && straight){
      return PokerLevel.STRAIGHT_FLUSH;
    }

    return PokerLevel.HIGH_CARD;
  }



  private boolean isStraight(List<Card> cards) {
    List<Card> collect = cards.stream().sorted((c1, c2) -> {
      return c1.getNumber().compareToIgnoreCase(c2.getNumber());
    }).collect(
        Collectors.toList());
    for(int i = 0; i < collect.size() - 1; i++){
      if(Integer.parseInt(collect.get(i).getNumber()) + 1 != Integer.parseInt(collect.get(i+1).getNumber())){
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
