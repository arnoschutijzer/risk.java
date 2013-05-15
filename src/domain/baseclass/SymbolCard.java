/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package domain.baseclass;

public class SymbolCard {
	private String type;
	
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
