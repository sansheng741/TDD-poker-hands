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
}