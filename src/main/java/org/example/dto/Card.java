package org.example.dto;

import java.util.Map;
import java.util.regex.Pattern;

public class Card implements Comparable<Card> {

    private final String value;
    private final Suit suit;

    public Card(String value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    private static final Pattern CARD_SYMBOL = Pattern.compile("(?<value>[2-9]|[AKQJ]|10)(?<suit>[HCDS])");

    public static Card parse(String symbol) {
        final var matcher = CARD_SYMBOL.matcher(symbol);

        if (!matcher.matches()) {
            throw new CardFormatException();
        }

        return new Card(
                matcher.group("value"),
                Suit.valueOfSymbol(matcher.group("suit"))
        );
    }

    private static final Map<String, Integer> RANKS = Map.of(
            "A", 14,
            "K", 13,
            "Q", 12,
            "J", 11
    );

    public Integer getRank() {
        return RANKS.containsKey(this.value)
                ? RANKS.get(this.value)
                : Integer.parseInt(this.value);
    }

    @Override
    public int compareTo(final Card card) {
        return Integer.compare(this.getRank(), card.getRank());
    }

    @Override
    public String toString() {
        return "Card{" +
                "value='" + value + '\'' +
                ", suit=" + suit +
                '}';
    }
}
