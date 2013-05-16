package domain.repositories;

import java.sql.SQLException;

import persistance.PersistanceController;

import domain.baseclass.GameSquare;
import domain.baseclass.Land;
import domain.baseclass.User;
/**
 * Holds the instances of all the GameSquare objects.
 * 
 * @author Thijs van der Burgt
 * @author Arno Schutijzer
 *
 */
public class GameSquareRepository {
	public static final int DIMX = 10, DIMY = 10;
	private GameSquare[][] map;
	
	/**
	 * Default constructor
	 */
	public GameSquareRepository(){
		this.map = new GameSquare[DIMX][DIMY];
	}
	/**
	 * Initialises a GameSquare on a specified position in the GameSquare array (map attribute)
	 * @param gs
	 * @param x
	 * @param y
	 * @throws IllegalArgumentException
	 */
	public void initSquare(GameSquare gs, int x, int y) throws IllegalArgumentException{
		this.map[x][y] = gs;
	}
	
	public void setUser(User user, int x, int y){
		((Land) this.map[x][y]).setUser(user);
	}
	
	public int getArmy(int x, int y){
		return ((Land) this.map[x][y]).getArmy();
	}
	
	public void addArmy(int x, int y){
		((Land) this.map[x][y]).addArmy();
	}
	
	public int getContinentNumber(int x, int y){
		return (int) ((Land) this.map[x][y]).getContinentNumber();
	}
	
	public User getUser(int x, int y){
		return ((Land) this.map[x][y]).getUser();
	}
	
	public GameSquare getGameSquare(int x, int y){
		return map[x][y];
	}
	
	public GameSquare[][] getMap() {
		return this.map;
	}
	
	public int getDIMX(){
		return DIMX;
	}
	
	public int getDIMY(){
		return DIMY;
	}

	/**
	 * Calls the generateMap in PersistanceController
	 * 
	 * @param mapNr
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see persistance.PersistanceController
	 */
	public void generateMap(int mapNr) throws ClassNotFoundException, SQLException {
		this.map = PersistanceController.getInstance().generateMap(mapNr, DIMX, DIMY);
	}
	
	/**
	 * Returns a textual representation of the attribute map
	 */
	public String toString(){
		String out = "";
		for(GameSquare[] gs : map){
			for(GameSquare l : gs){
				if(l instanceof Land){
					Land j = (Land)l;
					out += j.getName() + "." + j.getUser().getSymbol().getSymbol() + " | ";
				}else{
					out += l.getName() + " | ";
				}
			}
			out += "\n";
		}
		return out;
	}
	/**
	 * 
	 * @return String array with the Symbols of the GameSquares in map.
	 */
	public String[][] gameSquareSymbolStringArray(){
		String[][] symbols = new String[DIMX][DIMY];
		for(int i = 0; i < DIMY; i++){
			for(int a = 0; a<DIMX; a++){
				GameSquare gs = map[a][i];
				if(gs instanceof Land){
					symbols[a][i] = ((Land) gs).getUser().getSymbol().getSymbol();
				}else{
					symbols[a][i] = "";
				}
			}
		}
		return symbols;
	}
	/**
	 * 
	 * @return String array with the colors of the GameSquares in map.
	 */
	public String[][] gameSquareColorsStringArray() {
		String[][] mapColors = new String[DIMX][DIMY];
		for(int i = 0; i<DIMY; i++){
			for(int a = 0; a<DIMX; a++){
				GameSquare gs = map[a][i];
				mapColors[a][i] = gs.getColor();
			}
		}
		return mapColors;
	}
}
