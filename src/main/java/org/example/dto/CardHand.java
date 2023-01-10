package org.example.dto;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Stream;

public class CardHand {

    private final List<Card> cards;

    public CardHand(
            final Card card1,
            final Card card2,
            final Card card3,
            final Card card4,
            final Card card5
    ) {
        this.cards = Stream.of(card1, card2, card3, card4, card5)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public List<Card> showCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "CardHand{" +
                "cards=" + cards +
                '}';
    }
}
