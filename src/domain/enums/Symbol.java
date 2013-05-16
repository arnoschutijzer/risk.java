package domain.enums;
/**
 * This enum contains all the possible symbols a user can have.
 * @author Thijs van der Burgt
 *
 */
public enum Symbol {
	SYMBOL1("aod"),
	SYMBOL2("hazardous"),
	SYMBOL3("yinyang"),
	SYMBOL4("qlimax"),
	SYMBOL5("defqon"),
	SYMBOL6("bass");
	
	private String symbol;
	
	Symbol(String s){
		this.symbol = s;
	}
	
	public String getSymbol(){
		return this.symbol;
	}
}
