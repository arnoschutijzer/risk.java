/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package domain.repositories;

import java.sql.SQLException;

import persistance.PersistanceController;

import domain.baseclass.GameSquare;
import domain.baseclass.Land;
import domain.baseclass.User;

public class GameSquareRepository {
	public static final int DIMX = 10, DIMY = 10;
	private GameSquare[][] map;
	
	public GameSquareRepository(){
		this.map = new GameSquare[DIMX][DIMY];
	}
	
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

	public void generateMap(int mapNr) throws ClassNotFoundException, SQLException {
		this.map = PersistanceController.getInstance().generateMap(mapNr, DIMX, DIMY);
	}
	
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
