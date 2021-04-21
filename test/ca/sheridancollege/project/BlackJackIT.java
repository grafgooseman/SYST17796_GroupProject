package ca.sheridancollege.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Artem
 */
public class BlackJackIT {
    
    /**
     * Test of isBusted method, of class BlackJack.
     */
    @Test
    public void testIsBusted() {
        System.out.println("isBusted");
        int playerCardsValue = 22;
        boolean expResult = true;
        boolean result = BlackJack.isBusted(playerCardsValue);
        assertEquals(expResult, result);
    }

    /**
     * Test of isWon method, of class BlackJack.
     */
    @Test
    public void testIsWon() {
        System.out.println("isWon");
        int dealerCardsValue = 25;
        boolean endRound = false;
        boolean expResult = true;
        boolean result = BlackJack.isWon(dealerCardsValue, endRound);
        assertEquals(expResult, result);
    }
    
}
