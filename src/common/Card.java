package common;

public class Card
{
	private Suit suit;
	
	private Face face;
	
	public Card(Suit suit, Face face)
	{
		this.suit = suit;
		this.face = face;
		
	}
	
	public Suit getSuit()
	{
		return suit;
	}
	
	public Face getFace()
	{
		return face;
	}
	
	public enum Suit
	{
		CLUBS("Clubs"), DIAMONDS("Diamonds"), HEARTS("Hearts"), 
		SPADES("Spades");
		
		private final String suitString;
		
		Suit(String suitString)
		{
			this.suitString = suitString;
		}
		
		public String getSuitString()
		{
			return suitString;
		}
	}

	public enum Face
	{
		TWO("Two"), THREE("Three"), FOUR("Four"), FIVE("Five"), SIX("Six"), 
		SEVEN("Seven"), EIGHT("Eight"), NINE("Nine"), TEN("Ten"), 
		JACK("Jack"), QUEEN("Queen"), KING("King"), ACE("Ace");
		
		private final String faceString;
		
		Face(String faceString)
		{
			this.faceString = faceString;
		}
		
		public String getFaceString()
		{
			return faceString;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public String toString()
	{
		return "(" + suit.getSuitString() + ":" + face.getFaceString() + ")";
	}
}
