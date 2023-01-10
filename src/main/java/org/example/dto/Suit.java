package org.example.dto;

public enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES;

    public static Suit valueOfSymbol(String symbol) {
        return switch (symbol) {
            case "H" -> HEARTS;
            case "D" -> DIAMONDS;
            case "C" -> CLUBS;
            case "S" -> SPADES;
            default -> throw new CardFormatException();
        };
    }
}
