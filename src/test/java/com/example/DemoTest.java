package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DemoTest {

  private static Demo demo;

  @BeforeClass
  public static void setUp() {
    demo = new Demo();
  }

  @Test
  public void test01() {
    String result = demo.run();
    assertThat(result).isEqualTo("ABC");
  }


  @Test
  public void should_return_High_Card_when_judge_card_type_give_2H3D5S9CKD() {
    //given
    String card = "2H 3D 5S 9C KD";
    //when
    PokerGame pokerGame = new PokerGame();
    PokerLevel pokerLevel = pokerGame.judgeCardType(card);
    //then
    assertEquals(pokerLevel.getCardType(), "High Card");
  }

  @Test
  public void should_return_straight_flush_when_judge_card_type_give_3H4H5H6H7H() {
    //given
    String card = "3H 4H 5H 6H 7H";
    //when
    PokerGame pokerGame = new PokerGame();
    PokerLevel pokerLevel = pokerGame.judgeCardType(card);
    //then
    assertEquals(pokerLevel.getCardType(), "Straight flush");
  }

  @Test
  public void should_return_four_of_a_kind_when_judge_card_type_give_3H3D3S5C3D() {
    //given
    String card = "3H 3D 3S 5C 3D";
    //when
    PokerGame pokerGame = new PokerGame();
    PokerLevel pokerLevel = pokerGame.judgeCardType(card);
    //then
    assertEquals(pokerLevel.getCardType(), "Four of a Kind");
  }

  @Test
  public void should_return_four_of_a_kind_when_judge_card_type_give_3H3D5S5C3D() {
    //given
    String card = "3H 3D 5S 5C 3D";
    //when
    PokerGame pokerGame = new PokerGame();
    PokerLevel pokerLevel = pokerGame.judgeCardType(card);
    //then
    assertEquals(pokerLevel.getCardType(), "Full House");
  }

  @Test
  public void should_return_flush_when_judge_card_type_give_2H3H5H9HKH() {
    //given
    String card = "2H 3H 5H 9H KH";
    //when
    PokerGame pokerGame = new PokerGame();
    PokerLevel pokerLevel = pokerGame.judgeCardType(card);
    //then
    assertEquals(pokerLevel.getCardType(), "Flush");
  }

  @Test
  public void should_return_Straight_when_judge_card_type_give_3H4D5S6C7D() {
    //given
    String card = "3H 4D 5S 6C 7D";
    //when
    PokerGame pokerGame = new PokerGame();
    PokerLevel pokerLevel = pokerGame.judgeCardType(card);
    //then
    assertEquals(pokerLevel.getCardType(), "Straight");
  }

  @Test
  public void should_return_three_of_a_Kind_when_judge_card_type_give_3H3D5S9C3D() {
    //given
    String card = "3H 3D 5S 9C 3D";
    //when
    PokerGame pokerGame = new PokerGame();
    PokerLevel pokerLevel = pokerGame.judgeCardType(card);
    //then
    assertEquals(pokerLevel.getCardType(), "three of a Kind");
  }

  @Test
  public void should_return_two_pairs_when_judge_card_type_give_3H3D5S9C5D() {
    //given
    String card = "3H 3D 5S 9C 5D";
    //when
    PokerGame pokerGame = new PokerGame();
    PokerLevel pokerLevel = pokerGame.judgeCardType(card);
    //then
    assertEquals(pokerLevel.getCardType(), "Two Pairs");
  }

  @Test
  public void should_return_pair_when_judge_card_type_give_3H3D5S9CKD() {
    //given
    String card = "3H 3D 5S 9C KD";
    //when
    PokerGame pokerGame = new PokerGame();
    PokerLevel pokerLevel = pokerGame.judgeCardType(card);
    //then
    assertEquals(pokerLevel.getCardType(), "Pair");
  }

  @Test
  public void should_return_high_card_when_judge_card_type_give_2H3D5S9CKD() {
    //given
    String card = "2H 3D 5S 9C KD";
    //when
    PokerGame pokerGame = new PokerGame();
    PokerLevel pokerLevel = pokerGame.judgeCardType(card);
    //then
    assertEquals(pokerLevel.getCardType(), "High Card");
  }

  @Test
  public void should_return_white_win_when_compare_card_give_2H3D5S9CKD_and_2C3H4S8CAH() {
    //given
    String blackCard = "2H 3D 5S 9C KD";
    String whiteCard = "2C 3H 4S 8C AH";
    //when
    PokerGame pokerGame = new PokerGame();
    String result = pokerGame.compareCard(blackCard, whiteCard);
    //then
    assertEquals(result, "White wins");
  }

  @Test
  public void should_return_black_win_when_compare_card_give_2H4S5C2D4H_2S8SASQS3S() {
    //given
    String blackCard = "2H 4S 4C 2D 4H";
    String whiteCard = "2S 8S AS QS 3S";
    //when
    PokerGame pokerGame = new PokerGame();
    String result = pokerGame.compareCard(blackCard, whiteCard);
    //then
    assertEquals(result, "Black wins");
  }

  @Test
  public void should_return_black_win_when_compare_card_give_2H3D5S9CKD_and_2C3H4S8CKH() {
    //given
    String blackCard = "2H 3D 5S 9C KD";
    String whiteCard = "2C 3H 4S 8C KH";
    //when
    PokerGame pokerGame = new PokerGame();
    String result = pokerGame.compareCard(blackCard, whiteCard);
    //then
    assertEquals(result, "Black wins");
  }

  @Test
  public void should_return_tie_when_compare_card_give_2H3D5S9CKD_and_2D3H5C9SKH() {
    //given
    String blackCard = "2H 3D 5S 9C KD";
    String whiteCard = "2D 3H 5C 9S KH";
    //when
    PokerGame pokerGame = new PokerGame();
    String result = pokerGame.compareCard(blackCard, whiteCard);
    //then
    assertEquals(result, "Tie");
  }

  @Test
  public void should_return_white_win_when_compare_card_give_5H5C6S7SKD_and_2C3S8S8DTD() {
    //given
    String blackCard = "5H 5C 6S 7S KD";
    String whiteCard = "2C 3S 8S 8D TD";
    //when
    PokerGame pokerGame = new PokerGame();
    String result = pokerGame.compareCard(blackCard, whiteCard);
    //then
    assertEquals(result, "White wins");
  }

}