/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class GroupOfCards{

 private ArrayList<BlackJackCard> cards;
 
 public GroupOfCards(){
  //Create a new deck of playing cards
  this.cards = new ArrayList<BlackJackCard>();
 
 }
 
 //Add 52 playing cards to a deck
 public void createFullDeck(){
  
  for(Suit cardSuit : Suit.values()){
   
   for(Value cardValue : Value.values()){
    
    this.cards.add(new BlackJackCard(cardSuit,cardValue));
   }
  }
 }
 
 
//Shuffle deck of cards
public void shuffle(){
 
 ArrayList<BlackJackCard> tmpDeck = new ArrayList<BlackJackCard>();
 
 Random random = new Random();
 int randomCardIndex = 0;
 int originalSize = this.cards.size();
 for(int i = 0; i<originalSize;i++){
  
  randomCardIndex = random.nextInt((this.cards.size()-1 - 0) + 1) + 0;
  
  tmpDeck.add(this.cards.get(randomCardIndex));
  
  this.cards.remove(randomCardIndex);
 }
 
 this.cards = tmpDeck;
}
 
 
 //Remove a card from the deck
 public void removeCard(int i){
  this.cards.remove(i);
 }
 //Get card from deck
 public BlackJackCard getCard(int i){
  return this.cards.get(i);
 }
 
 //Add card to deck
 public void addCard(BlackJackCard addCard){
  this.cards.add(addCard);
 }
 
 
 public void draw(GroupOfCards comingFrom){
  //Add card to this deck from whatever deck its coming from
  this.cards.add(comingFrom.getCard(0));
  //Remove the card in the deck its coming from
  comingFrom.removeCard(0);
 }
 
 
 public String toString(){
  String cardListOutput = "";
  int i = 0;
  for(BlackJackCard aCard : this.cards){
   cardListOutput += "\n" + aCard.toString();
   i++;
  }
  return cardListOutput;
 }
 
 public void moveAllToDeck(GroupOfCards moveTo){
  int thisDeckSize = this.cards.size();
  
  for(int i = 0; i < thisDeckSize; i++){
   moveTo.addCard(this.getCard(i));
  }
  
  for(int i = 0; i < thisDeckSize; i++){
   this.removeCard(0);
  }
 }
 
 public int deckSize(){
  return this.cards.size();
 }
 
 //Calculate the value of deck
 public int cardsValue(){
  int totalValue = 0;
  int aces = 0;
  
  for(BlackJackCard aCard : this.cards){
   
   switch(aCard.getValue()){
   case TWO: totalValue += 2; break;
   case THREE: totalValue += 3; break;
   case FOUR: totalValue += 4; break;
   case FIVE: totalValue += 5; break;
   case SIX: totalValue += 6; break;
   case SEVEN: totalValue += 7; break;
   case EIGHT: totalValue += 8; break;
   case NINE: totalValue += 9; break;
   case TEN: totalValue += 10; break;
   case JACK: totalValue += 10; break;
   case QUEEN: totalValue += 10; break;
   case KING: totalValue += 10; break;
   case ACE: aces += 1; break;
   }   
  }
  
  
  //Aces worth 11 or 1 
  for(int i = 0; i < aces; i++){
   
   if (totalValue > 10){
    totalValue += 1;
   }
   else{
    totalValue += 11;
   }
  }
  
  return totalValue; 
 }
 
 
}
