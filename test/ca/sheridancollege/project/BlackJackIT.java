package ca.sheridancollege.project;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


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
