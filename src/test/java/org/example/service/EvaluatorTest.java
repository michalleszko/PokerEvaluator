package org.example.service;

import org.assertj.core.api.Assertions;
import org.example.dto.Card;
import org.example.dto.CardHand;
import org.example.dto.HandType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//TODO parametrise test in order to reduce code duplication
class EvaluatorTest {

    private Evaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new Evaluator();
    }

    @Test
    void evaluateFull() {
        //given
        final var hand = new CardHand(
                Card.parse("AH"),
                Card.parse("2H"),
                Card.parse("AC"),
                Card.parse("2C"),
                Card.parse("AD")
        );

        //when
        final var handType = evaluator.evaluate(hand);

        //then
        Assertions.assertThat(handType).isEqualTo(HandType.FULL_HOUSE);
    }

    @Test
    void evaluatePair() {
        //given
        final var hand = new CardHand(
                Card.parse("AH"),
                Card.parse("AD"),
                Card.parse("3C"),
                Card.parse("2C"),
                Card.parse("4D")
        );

        //when
        final var handType = evaluator.evaluate(hand);

        //then
        Assertions.assertThat(handType).isEqualTo(HandType.ONE_PAIR);
    }


    @Test
    void evaluateThreeOfKind() {
        //given
        final var hand = new CardHand(
                Card.parse("2H"),
                Card.parse("AD"),
                Card.parse("3C"),
                Card.parse("2C"),
                Card.parse("2D")
        );

        //when
        final var handType = evaluator.evaluate(hand);

        //then
        Assertions.assertThat(handType).isEqualTo(HandType.THREE_OF_A_KIND);
    }

    @Test
    void evaluateTwoPairs() {
        //given
        final var hand = new CardHand(
                Card.parse("QH"),
                Card.parse("8D"),
                Card.parse("QC"),
                Card.parse("8C"),
                Card.parse("2D")
        );

        //when
        final var handType = evaluator.evaluate(hand);

        //then
        Assertions.assertThat(handType).isEqualTo(HandType.TWO_PAIR);
    }
}