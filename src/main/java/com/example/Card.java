package com.example;

public class Card {
  String number;
  String suit;

  public Card() {
  }

  public Card(String number, String suit) {
    this.number = number;
    this.suit = suit;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getSuit() {
    return suit;
  }

  public void setSuit(String suit) {
    this.suit = suit;
  }
}
