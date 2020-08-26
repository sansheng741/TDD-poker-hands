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
    assertEquals("High Card",pokerLevel.getCardType());
  }

}