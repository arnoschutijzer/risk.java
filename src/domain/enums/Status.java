/**
 * @author Thijs van der Burgt
 * 
 * Enum defines possible ongoing useractions
 */

package domain.enums;

public enum Status {
	STATUS1("placingarmies"),
	STATUS2("selectingattacker"),
	STATUS3("selectingdefender"),
	STATUS4("exchangingcards");
	
	private String status;
	
	Status(String stat){
		this.status = stat;
	}
	
	public String getStatusMessage(){
		return this.status;
	}
}
