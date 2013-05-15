/**
* @author Arno Schutijzer & Thijs van der Burgt
**/

package domain;

import java.sql.SQLException;
import java.util.List;

import domain.baseclass.Game;
import domain.baseclass.GameSquare;
import domain.baseclass.Land;
import domain.baseclass.Map;
import domain.baseclass.User;
import domain.repositories.GameSquareRepository;
import domain.repositories.MapRepository;
import exceptions.IllegalGameSquareException;
import exceptions.InsufficientArmy;
import exceptions.UserAlreadyExistsException;

public class DomainController {
	private Game game;
	private MapRepository mapRepository;
	
	public DomainController() throws ClassNotFoundException, SQLException{
		mapRepository = new MapRepository();
	}
	
	public void startGame(String name){
		game = new Game(name);
	}
	
	public String getGameName(){
		return game.getGameName();
	}
	
	public GameSquareRepository getGameSquareRepository(){
		return game.getGameSquareRepository();
	}
	
	public Game getGame(){
		return this.game;
	}
	
	public void createUser(String string) throws UserAlreadyExistsException {
		game.createUser(string);
	}

	public List<User> getUsers() {
		return game.getUsers();
	}
	
	public User getUser(int index) {
		return game.getUser(index);
	}
	
	public User getUser(int x, int y){
		return game.getGameSquareRepository().getUser(x, y);
	}
	
	public void generateMap(int i) throws ClassNotFoundException, SQLException {
		game.generateMap(i);
	}

	public boolean attack(int attX, int attY, int defX, int defY, int attDice, int defDice) throws ClassCastException, IllegalGameSquareException, InsufficientArmy{
		return game.attack(attX, attY, defX, defY, attDice, defDice);
	}
	
	public void divideMap() {
		game.divideMap();
	}

	public String displayMap() {
		return game.displayMap();
	}
	
	public String[][] gameSquareSymbolStringArray(){
		return game.gameSquareSymbolStringArray();
	}
	
	public String[][] gameSquareColorsStringArray(){
		return game.gameSquareColorsStringArray();
	}
	
	public int getNumOfMaps(){
		return mapRepository.getNumOfMaps();
	}
	
	public Map getMap(int index){
		return mapRepository.getMap(index);
	}
	
	public void clearUsers(){
		game.clearUsers();
	}
	
	public String[] getMapNames(){
		return mapRepository.getMapNames();
	}
	
	public GameSquare getLand(int x, int y){
		return game.getLand(x, y);
	}
	
	public GameSquare getGameSquare(int x, int y){
		return game.getGameSquare(x, y);
	}
	
	public int getArmy(int x, int y){
		return ((Land) game.getGameSquare(x, y)).getArmy();
	}
	
	public void addArmy(int x, int y){
		game.addArmy(x, y);
	}
	
	public int calcArmy(User user){
		return game.calcArmy(user);
	}
	
	public int getContinentNumber(int x, int y){
		return ((Land) game.getGameSquare(x, y)).getContinentNumber();
	}
	
	public int getContinentNumbr(int x, int y){
		if(game.getGameSquare(x, y) instanceof Land)
			return ((Land) game.getGameSquare(x, y)).getContinentNumber();
		else
			return -1;
	}
	
	public int calcTurn(){
		return game.calcTurn();
	}
}
