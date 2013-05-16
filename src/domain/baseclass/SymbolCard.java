package domain.baseclass;
/**
 * Class defines the cards that can be exchanged for additional armies.
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public class SymbolCard {
	private String type;
	/**
	 * Default constructor for a SymbolCard.
	 * @param type
	 */
	public SymbolCard(String type){
		if(!(type.isEmpty() && type.equals(""))){
			this.type= type;
		}
		else
			throw new IllegalStateException();
	}
	
	public String getType(){
		return type;
	}
}
