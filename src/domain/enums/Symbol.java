/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package domain.enums;

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
