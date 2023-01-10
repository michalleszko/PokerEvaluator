package org.example.service;

import org.example.dto.Card;
import org.example.dto.CardHand;
import org.example.dto.HandType;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toUnmodifiableList;

public class Evaluator {

    public HandType evaluate(CardHand hand) {
        Objects.requireNonNull(hand, "hand");

        final var cards = hand.showCards();

        final var flush = isFlush(cards);
        final var straight = isStraight(cards);

        if (flush && straight) {
            return HandType.STRAIGHT_FLUSH;
        }

        final var groups = group(cards);

        final var firstGroup = groups.get(0);

        if (firstGroup.size() == 4) {
            return HandType.FOUR_OF_A_KIND;
        }

        if (groups.size() > 1 && groups.get(0).size() == 3 && groups.get(1).size() == 2) {
            return HandType.FULL_HOUSE;
        }

        if (flush) {
            return HandType.FLUSH;
        }

        if (straight) {
            return HandType.STRAIGHT;
        }

        if (firstGroup.size() == 3) {
            return HandType.THREE_OF_A_KIND;
        }

        if (groups.size() > 1 && firstGroup.size() == 2 && groups.get(1).size() == 2) {
            return HandType.TWO_PAIR;
        }

        if (firstGroup.size() == 2) {
            return HandType.ONE_PAIR;
        }

        return HandType.HIGHEST_CARD;
    }

    private boolean isFlush(final List<Card> hand) {
        final var firstCardSuit = hand.get(0).getSuit();

        return hand.stream()
                .map(Card::getSuit).filter(suit -> suit == firstCardSuit)
                .count() == hand.size();
    }

    private static boolean isStraight(List<Card> hand) {
        var previousRank = hand.get(0).getRank();

        for (int i = 1; i < hand.size(); i++) {
            final var currentRank = hand.get(i).getRank();
            if (previousRank != currentRank + 1) {
                return false;
            }

            previousRank = currentRank;
        }

        return true;
    }

    private static List<List<Card>> group(List<Card> hand) {
        final Comparator<List<Card>> sizeComparator = Comparator.comparing(List::size, Comparator.reverseOrder());

        return hand.stream()
                .collect(groupingBy(Card::getRank, toUnmodifiableList()))
                .values()
                .stream()
                .sorted(sizeComparator.thenComparing(list -> list.get(0).getRank(), Comparator.reverseOrder()))
                .toList();
    }
}
