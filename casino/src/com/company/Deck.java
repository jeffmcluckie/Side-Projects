package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public void fillDeck() {
        for (int i = 0; i < Card.Suit.getSuitLength(); i++) {
            for (int j = 0; j < Card.Value.getValueLength(); j++) {
                Card card = new Card(Card.Suit.getSuits(i), Card.Value.getValues(j));
                cards.add(card);
            }
        }
    }
    public void resetDeck(Deck deck) {
        int deckSize = this.cards.size();
        for (int i = 0; i < deckSize; i++) {
            deck.cards.add(this.cards.get(i));
        }
        for (int i = 0; i < deckSize; i++) {
            this.removeCard(0); /// check this
        }
    }
    public void removeCard(int index){
        cards.remove(index);
    }

    public Card retrieveCard(int index){
        return this.cards.get(index);
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public void getCard(Deck deck){
        cards.add(deck.cards.get(0));
        deck.removeCard(0);
    }

    public int getDeckSize(){
        return cards.size();
    }

    //@TODO blackjack specific, can separate out to make class more intuitive to use for other games
    public int getHandValue(){
        int value = 0;
        int aces = 0;

        for(Card card : cards){
            switch(card.getValue()){
                case TWO : value += 2; break;
                case THREE : value += 3; break;
                case FOUR : value += 4; break;
                case FIVE : value += 5; break;
                case SIX : value += 6; break;
                case SEVEN: value += 7; break;
                case EIGHT: value += 8; break;
                case NINE: value += 9; break;
                case TEN : value += 10; break;
                case JACK : value += 10; break;
                case QUEEN: value += 10; break;
                case KING: value += 10; break;
                case ACE : aces++; break;
                }
            }
            for(int i = 0; i < aces; i++) {
                if (value <= 10) {
                value += 11;
                } else {
                value += 1;
                }
        }
        return value;
    }

    public String toString(){
        String deck = "";
        for(Card card : cards){
            deck += card.toString() + '\n';
        }
        return deck;
    }

}

