package server;

import java.util.ArrayList;

import common.Card;
import common.Card.Face;
import common.Card.Suit;

/**
 * 
 * 
 * @author Evan Arroyo
 * @author Joshua Sims
 * 
 * @version 28 October 2016
 */
class CardSource implements ChargenSource<Card>
{
	/**
	 * 
	 */
	public static final int NUM_OF_CARDS_IN_A_DECK = 52;
	
	/**
	 * 
	 */
	public ArrayList<Card> deck = 
		new ArrayList<Card>(NUM_OF_CARDS_IN_A_DECK);
	
	/**
	 * 
	 */
	private int indexOfCardToSend;
	
	/**
	 * 
	 */
	CardSource()
	{
		for (
			int suit = 0; 
			suit < Suit.values().length;
			++suit)
		{
			for (
				int face = 0; 
				face < Face.values().length;
				++face)
			{
				deck.add(new Card(Suit.values()[suit], Face.values()[face]));
			}
		}
		
		indexOfCardToSend = 0;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	@Override
	public Card next()
	{
		Card cardToSend = deck.get(indexOfCardToSend);
		
		++indexOfCardToSend;
		if (indexOfCardToSend == NUM_OF_CARDS_IN_A_DECK)
		{
			indexOfCardToSend = 0;
		}
		
		return cardToSend;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	@Override
	public int itemsToSend()
	{
		return NUM_OF_CARDS_IN_A_DECK;
	}
}
