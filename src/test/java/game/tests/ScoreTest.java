package game.tests;

import Classes.Card;
import Classes.Dependencies;
import Classes.Hand;
import Classes.Score;
import enums.Rank;
import enums.Suite;
import navis.injection.Resettable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by mikehollibaugh on 11/16/16.
 */
public class ScoreTest {


    protected Hand mockedHand = null;

    public Resettable withMockHand() {
        mockedHand = mock(Hand.class);
        return Dependencies.hand.override(() -> mockedHand);
    }

    @Before
    public void setup() {
        withMockHand();
    }

    @After
    public void tearDown() throws Exception {
        Dependencies.hand.close();
    }

    Card eightOfClubs = new Card(Suite.Clubs, Rank.Eight);
    Card jackOfDiamonds = new Card(Suite.Diamonds, Rank.Jack);
    Card aceOfDiamonds = new Card(Suite.Diamonds, Rank.Ace);
    Card sevenOfHearts = new Card(Suite.Hearts, Rank.Seven);

    @Test
    public void calculates_correct_score() {
        HashSet<Card> theCards = new HashSet<>();
        theCards.add(eightOfClubs);
        theCards.add(sevenOfHearts);
        Score score = Dependencies.score.make();
        when(mockedHand.getCards()).thenReturn(theCards);

        assertEquals(score.getScore(mockedHand), 15);

    }

    @Test
    public void hand_with_ace_over_21_subtracts_10() {
        HashSet<Card> theCards = new HashSet<>();
        theCards.add(eightOfClubs);
        theCards.add(jackOfDiamonds);
        theCards.add(aceOfDiamonds);
        Score score = Dependencies.score.make();
        when(mockedHand.getCards()).thenReturn(theCards);

        assertEquals(score.getScore(mockedHand), 19);
    }

    @Test
    public void hand_with_ace_adds_11() {
        HashSet<Card> theCards = new HashSet<>();
        theCards.add(jackOfDiamonds);
        theCards.add(aceOfDiamonds);
        Score score = Dependencies.score.make();
        when(mockedHand.getCards()).thenReturn(theCards);

        assertEquals(score.getScore(mockedHand), 21);
    }

    @Test
    public void returns_0_for_empty_hand() {
        HashSet<Card> theCards = new HashSet<>();
        Score score = Dependencies.score.make();
        when(mockedHand.getCards()).thenReturn(theCards);

        assertEquals(score.getScore(mockedHand), 0);
    }
}
