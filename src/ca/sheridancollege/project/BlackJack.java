/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 *
 * @author Balraj
 */
public class BlackJack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //System.out.println("Play round of blackjack?");
        //Creates new deck
        GroupOfCards playingDeck = new GroupOfCards();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        //PlayerCards
        GroupOfCards playerCards = new GroupOfCards();

        //Player money
        double playerMoney = 50.0;

        //dealerCards 
        GroupOfCards dealerCards = new GroupOfCards();

        //user input
        Scanner userInput = new Scanner(System.in);

        //Round loop
        while (playerMoney > 0) {
            //Take Bet
            System.out.println("You have $" + playerMoney
                    + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            boolean endRound = false;
            if (playerBet > playerMoney) {
                //Break if they bet too much
                System.out.println("You dont have that much money to bet");
                break;
            }

            System.out.println("Dealing");
            //Player gets two cards
            playerCards.draw(playingDeck);
            playerCards.draw(playingDeck);

            //Dealer gets two cards
            dealerCards.draw(playingDeck);
            dealerCards.draw(playingDeck);

            //drawing new cards
            while (true) {
                //Display player cards
                System.out.println("Your Hand:"
                        + playerCards.toString());

                //Display Value
                System.out.println("Your hand is currently valued at: "
                        + playerCards.cardsValue());

                //Display dealer cards
                System.out.println("Dealer Hand: "
                        + dealerCards.getCard(0).toString() + " and [Unknown]");

                //Hit or Stand
                System.out.println("Would you like to (1)Hit or (2)Stand");
                int response = userInput.nextInt();
                //Hit
                if (response == 1) {
                    playerCards.draw(playingDeck);
                    System.out.println("You draw a:"
                            + playerCards.getCard(playerCards.deckSize() - 1).toString());
                    
                    //Bust if they go over 21
                    if(isBusted(playerCards.cardsValue())){
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }

                //Stand
                if (response == 2) {
                    break;
                }

            }

            //Reveal Dealer Cards
            System.out.println("Dealer Cards:" + dealerCards.toString());

            //Check dealer hand value vs player
            if ((dealerCards.cardsValue() > playerCards.cardsValue())
                    && endRound == false) {
                System.out.println("Dealer beats you "
                        + dealerCards.cardsValue() + " to "
                        + playerCards.cardsValue());
                playerMoney -= playerBet;
                endRound = true;
            }
            //Dealer bust
            while ((dealerCards.cardsValue() < 17) && endRound == false) {
                dealerCards.draw(playingDeck);
                System.out.println("Dealer draws: "
                        + dealerCards.getCard(dealerCards.deckSize() - 1).toString());
            }
            //Dealer hand value
            System.out.println("Dealers hand value: "
                    + dealerCards.cardsValue());

            //Determine if dealer busted
            if (isWon(dealerCards.cardsValue(),endRound)) {
                playerMoney += playerBet;
                endRound = true;
            }
            //push
            if ((dealerCards.cardsValue() == playerCards.cardsValue())
                    && endRound == false) {
                System.out.println("Push.");
                endRound = true;
            }
            //if player wins
            if ((playerCards.cardsValue() > dealerCards.cardsValue())
                    && endRound == false) {
                System.out.println("You win the hand.");
                playerMoney += playerBet;
                endRound = true;
            } else if (endRound == false) //dealer wins
            {
                System.out.println("Dealer wins.");
                playerMoney -= playerBet;
            }

            //End of hand - put cards back in deck
            playerCards.moveAllToDeck(playingDeck);
            dealerCards.moveAllToDeck(playingDeck);
            System.out.println("End of Round.");

        }
        //Game is over
        System.out.println("Game over, you lost :(");

        //Close Scanner
        userInput.close();

    }

    public static boolean isBusted(int playerCardsValue) {
        if (playerCardsValue > 21) {
            System.out.println("Bust. Currently valued at: "
                    + playerCardsValue);
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean isWon(int dealerCardsValue, boolean endRound) {
        if(dealerCardsValue > 21 && endRound == false){
            System.out.println("Dealer Busts. You win!");
            return true;
        } else {
            return false;
        }
    }

}
