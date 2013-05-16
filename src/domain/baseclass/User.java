package domain.baseclass;

import domain.enums.Symbol;
import java.util.*;
/**
 * This class is used to define a user.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public class User {
	private final int MINNAMELENGTH = 3, MAXNAMELENGTH = 15;
	private final Random rand= new Random();
	private String name;
	private static int userCount;
	private int unassignedArmy;
	boolean turn;
	private List<SymbolCard> symbolCards= new ArrayList<>();
	private Symbol symbol;
	/**
	 * The default constructor for a user.
	 * @param name
	 * @param symbol
	 */
	public User(String name, Symbol symbol) {
		this.setName(name);
		this.setSymbol(symbol);
		}
	
	public String getName() {
		return this.name;
	}
	/**
	 * This method sets the name attribute and throws an exception if the name does not meet the requirements.
	 * @param name
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) throws IllegalArgumentException{
		if(!name.equals("") && name.length() >= MINNAMELENGTH && name.length() <= MAXNAMELENGTH){
			this.name = name;
			userCount++;
		}
		else if(name.length() < MINNAMELENGTH)
			throw new IllegalArgumentException("tooShortNameException");
		else if(name.length() > MAXNAMELENGTH)
			throw new IllegalArgumentException("tooLongNameException");
	}
	/**
	 * This method adds a random symbolcard to the user.
	 */
	public void addSymbolCard(){
		int index= 1+rand.nextInt(3);
		
		switch(index){
		case 1:
			symbolCards.add(new SymbolCard("cannon"));
			break;
		case 2:
			symbolCards.add(new SymbolCard("infantry"));
			break;
		case 3:
			symbolCards.add(new SymbolCard("horse"));
			break;
		}
	}
	public int getUnassignedArmy(){
		return this.unassignedArmy;
	}
	public void addUnassignedArmy(int value){
		unassignedArmy+= value;
	}
	public static void setUserCount(int uC){
		userCount = uC;
	}
	public static int getUserCount(){
		return userCount;
	}
	public boolean isTurn() {
		return turn;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	public Symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	public List<SymbolCard> getCards(){
		return symbolCards;
	}
	public String toString(){
		return name;
	}
}
